package tmdtdemo.tmdt.dto.response;

import lombok.Data;

@Data
public class OrderResponse {
    private Long idOrder;
    private String checkIn;
    private String checkOut;
    private String roomName;
    private String roomCode;
    private boolean paymentStatus;
    private String paymentMethod;
    private String createdDate;
    private Double total;
    private String username;
}
