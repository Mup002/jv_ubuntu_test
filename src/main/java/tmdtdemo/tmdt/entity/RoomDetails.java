package tmdtdemo.tmdt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roomdetails")
public class RoomDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_room")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private AnotherService service;
}
