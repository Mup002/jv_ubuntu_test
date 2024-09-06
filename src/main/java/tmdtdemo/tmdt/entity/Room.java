package tmdtdemo.tmdt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    private Long id;
    @Column(name = "name_of_room")
    private String name;
    @Column(name = "number_of_room")
    private Long number;
    @Column(name = "type_of_room")
    private String type;
    @Column(name = "item_of_room")
    private String item;
    private String description;
    private String conditions;
    private boolean available;
    private Long remainRoom;
    private String slug;
}
