package tmdtdemo.tmdt.dto.request;

import lombok.Data;

@Data
public class OrderRequest {
    private Long roomId;
    private Long serviceId ;
    private Long roomskuId;
    private String checkIn;
    private String checkOut;
    private String roomName;
    private Double total;
    private String paymentMethod;
}
