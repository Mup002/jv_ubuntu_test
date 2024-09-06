package tmdtdemo.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tmdtdemo.tmdt.entity.OrderDetails;

import java.util.Date;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetails,Long> {
    OrderDetails findOrderDetailsById(Long id);

    // custom query
    @Query("SELECT DISTINCT od.roomSku.id FROM OrderDetails od WHERE od.checkin <= :checkout AND od.checkout >= :checkin")
    List<Long> findAllRoomSkuOrderedBetween(@Param("checkin") Date checkin, @Param("checkout") Date checkout);

}
