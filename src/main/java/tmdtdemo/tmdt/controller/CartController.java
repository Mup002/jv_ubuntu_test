package tmdtdemo.tmdt.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tmdtdemo.tmdt.dto.request.CartRequest;
import tmdtdemo.tmdt.dto.response.CartResponse;
import tmdtdemo.tmdt.service.CartService;
import tmdtdemo.tmdt.utils.BaseResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")

public class CartController {
    private final CartService cartService;
    @PostMapping ("/add")
    @Transactional
    public ResponseEntity<CartResponse> addToCar(@RequestBody CartRequest cartRequest, HttpServletRequest httpServletRequest){
        CartResponse response = cartService.addToCart(cartRequest,httpServletRequest.getHeader("x-client-username"));
        return ResponseEntity.ok(response);
    }
    @PostMapping("/getByUsername")
    public ResponseEntity<Object> get(HttpServletRequest httpServletRequest){
        CartResponse response = cartService.getByUsername(httpServletRequest.getHeader("x-client-username"));
        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            BaseResponse baseResponse = BaseResponse.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST.value()).substring(0,3))
                    .message("Cart dont exits").build();
            return ResponseEntity.ok(baseResponse);
        }

    }
    @DeleteMapping("/delete/orderBy")
    public ResponseEntity<BaseResponse> delete(HttpServletRequest httpServletRequest){
        String rs = cartService.cancelOrder(httpServletRequest.getHeader("x-client-username"));
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(rs);
        baseResponse.setCode(HttpStatus.OK.toString());
        return ResponseEntity.ok(baseResponse);
    }

}
