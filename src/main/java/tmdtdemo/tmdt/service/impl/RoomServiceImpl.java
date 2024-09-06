package tmdtdemo.tmdt.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tmdtdemo.tmdt.dto.response.*;

import tmdtdemo.tmdt.entity.Room;
import tmdtdemo.tmdt.entity.RoomDetails;
import tmdtdemo.tmdt.entity.RoomSku;
import tmdtdemo.tmdt.repository.*;
import tmdtdemo.tmdt.service.CartService;
import tmdtdemo.tmdt.service.OrderService;
import tmdtdemo.tmdt.service.RoomService;
import tmdtdemo.tmdt.utils.AppConstants;
import tmdtdemo.tmdt.utils.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomDetailRepository roomDetailRepository;
    private final RoomSkuRepository roomSkuRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartService cartService;

    @Override
    public List<RoomResponse> getAllRoom(Date checkIn, Date checkOut) {
        List<Room> rooms = roomRepository.findAll().stream().toList();
        List<RoomResponse> responses = new ArrayList<>();
        List<Long> roomSkuId = orderDetailRepository.findAllRoomSkuOrderedBetween(checkIn,checkOut);
        List<CartResponse> cartResponses = cartService.getAllCart();

        if(cartResponses != null){
            if(cartResponses.size() > 0 ){
                for(CartResponse c : cartResponses){
                    roomSkuId.add(roomSkuRepository.findRoomSkuByCodeRoom(c.getCodeRoom()).getId());
                }
            }
        }
        for(Room r : rooms){
            RoomResponse response = new RoomResponse();
            response.setId(r.getId());
            response.setRoomType(r.getType());
            response.setRoomName(r.getName());
            response.setAvailable(r.isAvailable());
            response.setDescription(r.getDescription());
            response.setConditions(r.getConditions());
//            response.setRemainRoom(r.getRemainRoom());
            List<String> item = Arrays.stream(r.getItem().split("-")).collect(Collectors.toList());
            response.setItem(item);
            List<RoomDetails> roomDetails = roomDetailRepository.findRoomDetailsByRoomId(r.getId());
            List<AnotherServiceResponse> services = new ArrayList<>();
            for(RoomDetails roomD : roomDetails){
                services.add(Mapper.serviceToResponse(roomD.getService(),null));
            }

            List<RoomSkuCustom> roomSkuCustoms = roomSkuRepository.findRoomSkuByIdRoom(r.getId())
                            .stream()
                                    .map(sku -> {
                                        RoomSkuCustom skuCustom = new RoomSkuCustom();
                                        skuCustom.setId(sku.getId());
                                        skuCustom.setCodename(sku.getCodeRoom());
                                        skuCustom.setStatus(roomSkuId.contains(sku.getId()) ? "Unavailable" : "Available");
                                        return skuCustom;
                                    }).collect(Collectors.toList());
            Long remain =  roomSkuCustoms.stream().filter(c -> c.getStatus().equals("Available")).count();
            response.setRemainRoom(remain);
            response.setRoomSkus(roomSkuCustoms);
            response.setServiceResponse(services);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public RoomResponse getRoomById(Long id) {
        return Mapper.roomToResponse(roomRepository.findRoomById(id));
    }

    @Override
    public RoomSkuResponse getAllRoomSkuWithPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<RoomSku> roomSkuPage = roomSkuRepository.findAll(pageable);
        List<RoomSku> roomSkus = roomSkuPage.getContent();
        return new RoomSkuResponse(
                Mapper.roomToListCustomRoomResponse(roomSkus),
                pageNumber,
                pageSize,
                roomSkuPage.getTotalPages(),
                (int) roomSkuPage.getTotalElements(),
                roomSkuPage.isLast()
        );
    }

    @Override
    public RoomSkuResponse getAllRoomSkuWithPaginationWithSoring(Integer pageNumber, Integer pageSize, String sortBy, String dir) {
        Sort sort = dir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<RoomSku> roomSkuPage = roomSkuRepository.findAll(pageable);
        List<RoomSku> roomSkus = roomSkuPage.getContent();
        return new RoomSkuResponse(
                Mapper.roomToListCustomRoomResponse(roomSkus),
                pageNumber,
                pageSize,
                roomSkuPage.getTotalPages(),
                (int) roomSkuPage.getTotalElements(),
                roomSkuPage.isLast()
        );
    }

    @Override
    public RoomSkuResponse getAllRoomSkuWithPaginationAndSelected(Integer pageNumber, Integer pageSize, Date checkIn, Date checkOut, String status, String roomname) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<Long> roomSkuOrdered = orderDetailRepository.findAllRoomSkuOrderedBetween(checkIn,checkOut);
        Page<RoomSku> roomSkuPage = null;
        Long roomId = null;
        if(!roomname.equalsIgnoreCase(AppConstants.ROOM_NAME)){
            Room room = roomRepository.findRoomBySlug(roomname);
            roomId = room.getId();
        }
        if(!status.equalsIgnoreCase(AppConstants.STATUS) && roomname.equalsIgnoreCase(AppConstants.ROOM_NAME)){
            roomSkuPage = roomSkuRepository.findRoomSkuByIdList(roomSkuOrdered,pageable);
        } else if(status.equalsIgnoreCase(AppConstants.STATUS) && roomname.equalsIgnoreCase(AppConstants.ROOM_NAME)) {
            roomSkuPage = roomSkuRepository.findRoomSkuByNotInIdList(roomSkuOrdered,pageable);
        }else if(!status.equalsIgnoreCase(AppConstants.STATUS) && !roomname.equalsIgnoreCase(AppConstants.ROOM_NAME)){
            roomSkuPage =  roomSkuRepository.findRoomSkuByIdListAndRoom(roomSkuOrdered,roomId,pageable);
        }else{
            roomSkuPage =  roomSkuRepository.findRoomSkuByNotInIdListAndRoom(roomSkuOrdered,roomId,pageable);
        }
        List<RoomSku> roomSkus = roomSkuPage.getContent();
        return new RoomSkuResponse(
                Mapper.roomToListCustomRoomResponse(roomSkus),
                pageNumber,
                pageSize,
                roomSkuPage.getTotalPages(),
                (int) roomSkuPage.getTotalElements(),
                roomSkuPage.isLast()
        );
    }

    @Override
    public boolean checkRoomHasOrderedByTime(Date checkIn, Date checkOut, String codeRoom) {
        List<Long> idRoomSkuOrdered = orderDetailRepository.findAllRoomSkuOrderedBetween(checkIn,checkOut);
        RoomSku sku = roomSkuRepository.findRoomSkuByCodeRoom(codeRoom);
        if(idRoomSkuOrdered.contains(sku.getId())){
            return true;
        }
        return false;
    }

}

