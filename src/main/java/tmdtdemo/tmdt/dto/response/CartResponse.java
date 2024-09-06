package tmdtdemo.tmdt.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartResponse {

    private String username;
    private String createdAt;
    private Double total;
    private AnotherServiceResponse serviceResponses ;
    private String roomName;
    private Long roomId;
    private String checkIn;
    private String checkOut;
    private String codeRoom;
    private String time;
    private String peopleDetails;
}
