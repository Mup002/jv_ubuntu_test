package tmdtdemo.tmdt.dto.request;

import lombok.Data;

@Data
public class CartRequest {
    private Long roomId;
    private Long serviceId ;
    private String checkIn;
    private String checkOut;
    private String roomName;
    private Double total;
    private String peopleDetails;
    private String codeRoom;
}
