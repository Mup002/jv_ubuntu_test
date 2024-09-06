package tmdtdemo.tmdt.dto.response;

import tmdtdemo.tmdt.entity.RoomSku;

import java.util.List;

public record RoomSkuResponse(
        List<CustomRoomResponse> roomSkus,
        Integer pageNumber,
        Integer pageSize,
        int totalElements,
        int totalPages,
        boolean isLast
) {
}
