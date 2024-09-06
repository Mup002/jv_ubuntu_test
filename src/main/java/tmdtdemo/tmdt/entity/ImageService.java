package tmdtdemo.tmdt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "img_services")
public class ImageService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_img")
    private Long id;

    private String src;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service")
    private AnotherService service;
}
