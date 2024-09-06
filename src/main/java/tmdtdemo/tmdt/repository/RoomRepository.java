package tmdtdemo.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tmdtdemo.tmdt.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findRoomById(Long id);
    Room findRoomBySlug(String slug);
}
