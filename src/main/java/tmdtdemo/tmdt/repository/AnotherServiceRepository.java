package tmdtdemo.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tmdtdemo.tmdt.entity.AnotherService;

import java.util.List;

public interface AnotherServiceRepository extends JpaRepository<AnotherService,Long> {
    AnotherService findServiceById(Long id);
    //custom query
    @Query(value = "SELECT *  FROM Services s where s.available=1 and s.type= :type",nativeQuery = true)
    List<AnotherService> findServiceAvailableAndType(String type);
}
