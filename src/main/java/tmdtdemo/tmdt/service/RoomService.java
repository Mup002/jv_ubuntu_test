package tmdtdemo.tmdt.service;

import org.springframework.data.domain.Page;
import tmdtdemo.tmdt.dto.response.CustomRoomResponse;
import tmdtdemo.tmdt.dto.response.RoomResponse;
import tmdtdemo.tmdt.dto.response.RoomSkuResponse;
import tmdtdemo.tmdt.entity.RoomSku;

import java.util.Date;
import java.util.List;

public interface RoomService {
    List<RoomResponse> getAllRoom(Date checkIn, Date checkOut);
    RoomResponse getRoomById(Long id);

    RoomSkuResponse getAllRoomSkuWithPagination(Integer pageNumber, Integer pageSize);
    RoomSkuResponse getAllRoomSkuWithPaginationWithSoring(Integer pageNumber, Integer pageSize, String sortBy, String dir);
    RoomSkuResponse getAllRoomSkuWithPaginationAndSelected(Integer pageNumber, Integer pageSize, Date checkIn,Date checkOut, String status, String roomname);
    boolean checkRoomHasOrderedByTime(Date checkIn, Date checkOut, String codeRoom);

}
