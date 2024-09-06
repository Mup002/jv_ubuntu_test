package tmdtdemo.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmdtdemo.tmdt.entity.RoomDetails;

import java.util.List;

public interface RoomDetailRepository extends JpaRepository<RoomDetails,Long> {
    List<RoomDetails> findRoomDetailsByRoomId(Long roomId);
    RoomDetails findRoomDetailsByRoomIdAndServiceId(Long roomId,Long serviceId);
}
