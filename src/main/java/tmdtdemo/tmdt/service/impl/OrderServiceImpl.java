package tmdtdemo.tmdt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tmdtdemo.tmdt.common.PaymentMethod;
import tmdtdemo.tmdt.dto.request.OrderRequest;
import tmdtdemo.tmdt.dto.response.OrderDetailsResponse;
import tmdtdemo.tmdt.dto.response.OrderResponse;
import tmdtdemo.tmdt.entity.*;
import tmdtdemo.tmdt.exception.BaseException;
import tmdtdemo.tmdt.exception.ResourceNotFoundException;
import tmdtdemo.tmdt.repository.OrderDetailRepository;
import tmdtdemo.tmdt.repository.RoomDetailRepository;
import tmdtdemo.tmdt.repository.RoomSkuRepository;
import tmdtdemo.tmdt.service.OrderService;
import tmdtdemo.tmdt.service.RoomService;
import tmdtdemo.tmdt.service.UserService;
import tmdtdemo.tmdt.utils.DateFormat;
import tmdtdemo.tmdt.utils.Mapper;

import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderDetailRepository orderDetailRepository;
    private final UserService userService;
    private final RoomDetailRepository roomDetailRepository;
    private final RoomSkuRepository roomSkuRepository;
    private final RoomService roomService;
    @Override
    public String orderByUser(OrderRequest request, String username) {
        RoomSku sku = roomSkuRepository.findRoomSkuById(request.getRoomskuId());
        if(roomService.checkRoomHasOrderedByTime(DateFormat.convertStringToDate(request.getCheckIn()),DateFormat.convertStringToDate(request.getCheckOut()),sku.getCodeRoom())){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Room with id : " + request.getRoomId() + " has ordered in " + request.getCheckIn() + " to " + request.getCheckOut());
        }
        User user = userService.findUserByUsername(username);
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCheckin(DateFormat.convertStringToDate(request.getCheckIn()));
        orderDetails.setCheckout(DateFormat.convertStringToDate(request.getCheckOut()));
        Date now = new Date();
        orderDetails.setOrderDate(now);
        RoomDetails roomDetails = roomDetailRepository.findRoomDetailsByRoomIdAndServiceId(request.getRoomId(), request.getServiceId());
        if(!ObjectUtils.isEmpty(roomDetails)){
            orderDetails.setRoomDetails(roomDetails);
        }else{
            throw new ResourceNotFoundException("Cant find roomdetail with: " + request.getRoomId() + " and " + request.getServiceId());
        }

        orderDetails.setUser(user);
        orderDetails.setPaymentMethod(PaymentMethod.PRE_BOOKING);
        orderDetails.setTotal(request.getTotal());
        orderDetails.setPaymentStatus(false);
        orderDetails.setRoomSku(sku);
        orderDetailRepository.save(orderDetails);
        return "done";
    }

    @Override
    public String orderByAdmin(OrderRequest request, String username) {
        RoomSku sku = roomSkuRepository.findRoomSkuById(request.getRoomskuId());
        if(roomService.checkRoomHasOrderedByTime(DateFormat.convertStringToDate(request.getCheckIn()),DateFormat.convertStringToDate(request.getCheckOut()),sku.getCodeRoom())){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Room with id : " + request.getRoomId() + " has ordered in " + request.getCheckIn() + " to " + request.getCheckOut());
        }
        User user = userService.findUserByUsername(username);
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCheckin(DateFormat.convertStringToDate(request.getCheckIn()));
        orderDetails.setCheckout(DateFormat.convertStringToDate(request.getCheckOut()));
        Date now = new Date();
        orderDetails.setOrderDate(now);
        RoomDetails roomDetails = roomDetailRepository.findRoomDetailsByRoomIdAndServiceId(request.getRoomId(), request.getServiceId());
        if(!ObjectUtils.isEmpty(roomDetails)){
            orderDetails.setRoomDetails(roomDetails);
        }else{
            throw new ResourceNotFoundException("Cant find roomdetail with: " + request.getRoomId() + " and " + request.getServiceId());
        }

        orderDetails.setUser(user);
        orderDetails.setPaymentMethod(PaymentMethod.PRE_BOOKING);
        orderDetails.setTotal(request.getTotal());
        orderDetails.setPaymentStatus(false);
        orderDetails.setRoomSku(sku);
        orderDetailRepository.save(orderDetails);
        return "done";
    }

    @Override
    public List<OrderResponse> getAllOrderByAdmin() {
        return Mapper.orderToListResponse(orderDetailRepository.findAll()).stream().toList();
    }

    @Override
    public OrderDetailsResponse getOrderDetailsById(Long idOrder) {
        return Mapper.orderToOrderDetailsResponse(orderDetailRepository.findOrderDetailsById(idOrder));
    }

    @Override
    public List<Long> getAllRoomSkuBetween(Date checkin, Date checkout) {
        return orderDetailRepository.findAllRoomSkuOrderedBetween(checkin,checkout);
    }
}
