package tmdtdemo.tmdt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "room_skus")
public class RoomSku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomsku_id")
    private Long id;

    @Column(name = "code_room")
    private String codeRoom;

    @Column(name = "location_floor")
    private String location_floor;

    private boolean isPublish;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
