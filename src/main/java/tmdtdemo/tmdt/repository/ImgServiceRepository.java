package tmdtdemo.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tmdtdemo.tmdt.entity.ImageService;

@Repository
public interface ImgServiceRepository extends JpaRepository<ImageService,Long> {
    //custom query
    @Query(value = "SELECT i.src from ImageService i where i.service.id = :serviceId")
    String findSrcByIdService(Long serviceId);
}
