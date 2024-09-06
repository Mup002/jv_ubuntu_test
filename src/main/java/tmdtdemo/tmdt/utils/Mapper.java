package tmdtdemo.tmdt.utils;

import tmdtdemo.tmdt.common.CommentLevel;
import tmdtdemo.tmdt.dto.response.*;
import tmdtdemo.tmdt.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    // map entity user to response
    public static RefreshTokenResponse refreshTokenToResponse(RefreshToken rf){
        RefreshTokenResponse response = new RefreshTokenResponse();
        response.setToken(rf.getRefreshToken());
        response.setExpiration(rf.getRefreshExpiration());
        return response;
    }

    // map entity service to response
    public static AnotherServiceResponse serviceToResponse(AnotherService sv,String url){
        AnotherServiceResponse response = new AnotherServiceResponse();
        response.setId(sv.getId());
        response.setDescription(sv.getDescription());
        List<String> details = Arrays.stream(sv.getDetails().split("-")).collect(Collectors.toList());
        response.setDetails(details);
        response.setPrice(sv.getPrice());
        if(url != null){
            response.setUrl(url);
        }
        return response;
    }

    // map list entity service to list response
    public static List<AnotherServiceResponse> serviceToListReponse(List<AnotherService> svLst){
        List<AnotherServiceResponse> responses = new ArrayList<>();
        for(AnotherService s : svLst){
            responses.add(serviceToResponse(s,null));
        }
        return responses;
    }

    // map entity room to response
    public static RoomResponse roomToResponse(Room r){
        RoomResponse response = new RoomResponse();
        response.setId(r.getId());
        response.setNumber(r.getNumber());
        response.setRoomName(r.getName());
        response.setRoomType(r.getType());
        response.setConditions(r.getConditions());
        response.setDescription(r.getDescription());
        response.setRemainRoom(r.getRemainRoom());
        response.setAvailable(r.isAvailable());
        List<String> itemLst = Arrays.stream(r.getItem().split("-")).collect(Collectors.toList());
        response.setItem(itemLst);
        return response;
    }

    // map list entity room to response
    public static List<RoomResponse> roomToListResponse(List<Room> rooms){
        List<RoomResponse> responses = new ArrayList<>();
        for(Room r : rooms){
            responses.add(roomToResponse(r));
        }
        return responses;
    }

    // map entity comment to response
    public static CommentResponse commentToResponse (Comment comment){
        CommentResponse response = new CommentResponse();
        response.setUsername(comment.getUser().getUsername());
        response.setCreatedDate(DateFormat.convertDateToString(comment.getCreatedDate()));
        response.setText(comment.getText());
        response.setCmtId(comment.getId());
        response.setLevel(comment.getCommentLevel().toString());
        if(comment.getCommentLevel().equals(CommentLevel.LEVEL_1)){
            response.setCmtParentId(null);
            response.setCmtParentUsername(null);
        }else{
            response.setCmtParentId(comment.getParentComment().getId());
            if(comment.getUser().getId() == comment.getParentComment().getUser().getId()){
                response.setCmtParentUsername(null);
            }else{
                response.setCmtParentUsername(comment.getParentComment().getUser().getUsername());
            }
        }
        response.setRoomId(comment.getRoom().getId());

        return response;
    }

    // map comment to list response
    public static  List<CommentResponse> commentToListResponse(List<Comment> comments){
        List<CommentResponse> responses = new ArrayList<>();
        for(Comment c : comments){
            responses.add(commentToResponse(c));
        }
        return responses;
    }

    public static OrderResponse orderToOrderResponse(OrderDetails orderDetails){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setIdOrder(orderDetails.getId());
        orderResponse.setCheckOut(DateFormat.convertDateToString(orderDetails.getCheckout()));
        orderResponse.setCheckIn(DateFormat.convertDateToString(orderDetails.getCheckin()));
        orderResponse.setPaymentStatus(orderDetails.isPaymentStatus());
        orderResponse.setTotal(orderDetails.getTotal());
        orderResponse.setRoomCode(orderDetails.getRoomSku().getCodeRoom());
        orderResponse.setPaymentMethod(orderDetails.getPaymentMethod().toString());
        orderResponse.setCreatedDate(DateFormat.convertDateToString(orderDetails.getOrderDate()).split(" ")[1]);
        orderResponse.setRoomName(orderDetails.getRoomDetails().getRoom().getName());
        orderResponse.setUsername(orderDetails.getUser().getUsername());
        return orderResponse;
    }

    public static List<OrderResponse> orderToListResponse(List<OrderDetails> orderDetails){
        List<OrderResponse> orderResponses = new ArrayList<>();
        for(OrderDetails o : orderDetails){
            orderResponses.add(orderToOrderResponse(o));
        }
        return orderResponses;
    }

    public static OrderDetailsResponse orderToOrderDetailsResponse(OrderDetails orderDetails){
        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
        orderDetailsResponse.setEmail(orderDetails.getUser().getEmail());
        orderDetailsResponse.setUsername(orderDetails.getUser().getUsername());
        orderDetailsResponse.setPhone(orderDetails.getUser().getPhone());

        orderDetailsResponse.setCreatedDate(DateFormat.convertDateToString(orderDetails.getOrderDate()).split(" ")[1]);
        orderDetailsResponse.setPaymentMethod(orderDetails.getPaymentMethod().toString());

        orderDetailsResponse.setRoomName(orderDetails.getRoomDetails().getRoom().getName());
        orderDetailsResponse.setRoomCode(orderDetails.getRoomSku().getCodeRoom());
        orderDetailsResponse.setCheckIn(DateFormat.convertDateToString(orderDetails.getCheckin()).split(" ")[1]);
        orderDetailsResponse.setCheckOut(DateFormat.convertDateToString(orderDetails.getCheckout()).split(" ")[1]);
        orderDetailsResponse.setPaymentStatus(orderDetails.isPaymentStatus());
        orderDetailsResponse.setTotal(orderDetails.getTotal());

        List<String>services = Arrays.stream(orderDetails.getRoomDetails().getService().getDetails().split("-")).collect(Collectors.toList());
        orderDetailsResponse.setDetailsService(services);

        return orderDetailsResponse;
    }

    public static CustomRoomResponse roomToCustomRoomResponse(RoomSku room){
        CustomRoomResponse customRoomResponse = new CustomRoomResponse();
        customRoomResponse.setCoderoom(room.getCodeRoom());
        customRoomResponse.setRoomname(room.getRoom().getName());
        customRoomResponse.setId(room.getId());
        return customRoomResponse;
    }
    public static List<CustomRoomResponse> roomToListCustomRoomResponse(List<RoomSku> roomSkus){
        List<CustomRoomResponse> responses = new ArrayList<>();
        for(RoomSku s : roomSkus){
            responses.add(roomToCustomRoomResponse(s));
        }
        return responses;
    }
}
