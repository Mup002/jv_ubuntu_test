package tmdtdemo.tmdt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tmdtdemo.tmdt.common.PaymentMethod;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderdetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false,updatable = false)
    private Date orderDate;

    @Column(name = "total_price")
    private Double total;

    private Date checkin;

    private Date checkout;

    @ManyToOne
    @JoinColumn(name = "roomsku_id")
    private RoomSku roomSku;

    private boolean paymentStatus;
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "roomdetails_id")
    private RoomDetails roomDetails;
}
