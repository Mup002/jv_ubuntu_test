package tmdtdemo.tmdt.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDetailsResponse {
    private String createdDate;
    private String paymentMethod;

    private String username;
    private String email;
    private String phone;

    private String roomName;
    private String roomCode;
    private String checkIn;
    private String checkOut;
    private Double total;
    private boolean paymentStatus;

    private List<String> detailsService = new ArrayList<>();
}
