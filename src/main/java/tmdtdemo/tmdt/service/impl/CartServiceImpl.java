package tmdtdemo.tmdt.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tmdtdemo.tmdt.dto.request.CartRequest;
import tmdtdemo.tmdt.dto.response.CartResponse;
import tmdtdemo.tmdt.dto.response.AnotherServiceResponse;
import tmdtdemo.tmdt.exception.ResourceNotFoundException;
import tmdtdemo.tmdt.service.BaseRedisService;
import tmdtdemo.tmdt.service.CartService;
import tmdtdemo.tmdt.service.AnotherServiceService;
import tmdtdemo.tmdt.utils.ChangeObject;
import tmdtdemo.tmdt.utils.DateFormat;
import tmdtdemo.tmdt.utils.HelperUtils;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final BaseRedisService baseRedisService;
    private final AnotherServiceService serviceService;
    @Override
    public CartResponse addToCart(CartRequest cardRequest, String username) {
        CartResponse cardResponse = new CartResponse();
        cardResponse.setUsername(username);
        cardResponse.setCreatedAt(DateFormat.dateFormatWithLocate(String.valueOf(new Date())));
        cardResponse.setCheckIn(cardRequest.getCheckIn());
        cardResponse.setCheckOut(cardRequest.getCheckOut());
        cardResponse.setRoomName(cardRequest.getRoomName());
        cardResponse.setTotal(cardRequest.getTotal());
        cardResponse.setRoomId(cardRequest.getRoomId());
        cardResponse.setCodeRoom(cardRequest.getCodeRoom());
        cardResponse.setPeopleDetails(cardRequest.getPeopleDetails());
        cardResponse.setServiceResponses(serviceService.getServiceById(cardRequest.getServiceId()));
        baseRedisService.hashSet(HelperUtils.cartBuilderRedisKey(username), "cartData", ChangeObject.objectToJson(cardResponse));
        baseRedisService.setTimeToLive(HelperUtils.cartBuilderRedisKey(username),5 * 60 * 60);
        return cardResponse;
    }

    @Override
    public CartResponse getByUsername(String username) {
        String cartKey = HelperUtils.cartBuilderRedisKey(username);
        CartResponse cartResponse = new CartResponse();
        if(baseRedisService.hashExists(cartKey,"cartData")){
            Object object = baseRedisService.hashGet(cartKey,"cartData");
            String jsonString = (String) object; // Giả sử dữ liệu được lưu trữ dưới dạng chuỗi JSON
            Gson gson = new Gson();
            cartResponse = gson.fromJson(jsonString, CartResponse.class);

            log.info("cart has exists");
            return cartResponse;
        }else{
            return null;
        }

    }

    @Override
    public String cancelOrder(String username) {
        String cartKey = HelperUtils.cartBuilderRedisKey(username);
        if(baseRedisService.hashExists(cartKey,"cartData")){
            baseRedisService.delete(cartKey,"cartData");
            return "done";
        }else{
            throw new ResourceNotFoundException("Khong tim thay gio hang cua : "+ username);
        }
    }

    @Override
    public List<CartResponse> getAllCart() {
        List<CartResponse> responses = new ArrayList<>();
        Set<String> keys = baseRedisService.getKeysByPattern("cart_userId=*");
        if(keys.isEmpty()){
            return null;
        }else{
            for(String key : keys){
                Object object = baseRedisService.hashGet(key,"cartData");
                String json = (String) object;
                Gson gson = new Gson();
                CartResponse cartResponse = gson.fromJson(json,CartResponse.class);
                responses.add(cartResponse);
            }
            return responses;
        }
    }
}
