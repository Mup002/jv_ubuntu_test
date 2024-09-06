package tmdtdemo.tmdt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tmdtdemo.tmdt.entity.RoomSku;

import java.util.List;

public interface RoomSkuRepository extends JpaRepository<RoomSku,Long> {
    RoomSku findRoomSkuById(Long roomskuId);
    RoomSku findRoomSkuByCodeRoom(String code);

    //custom query
    @Query(value = "select * from room_skus where roomsku_id IN :idList",nativeQuery = true)
    Page<RoomSku> findRoomSkuByIdList(List<Long> idList, Pageable pageable);

    @Query(value = "select * from room_skus where roomsku_id NOT IN :idList",nativeQuery = true)
    Page<RoomSku> findRoomSkuByNotInIdList(List<Long> idList, Pageable pageable);

    @Query(value = "select * from room_skus where roomsku_id IN :idList AND room_id= :roomId",nativeQuery = true)
    Page<RoomSku> findRoomSkuByIdListAndRoom(List<Long> idList,Long roomId, Pageable pageable);

    @Query(value = "select * from room_skus where roomsku_id NOT IN :idList AND room_id= :roomId",nativeQuery = true)
    Page<RoomSku> findRoomSkuByNotInIdListAndRoom(List<Long> idList,Long roomId, Pageable pageable);

    @Query(value = "select * from room_skus where room_id= :roomId", nativeQuery = true)
    List<RoomSku> findRoomSkuByIdRoom( Long roomId);

}
