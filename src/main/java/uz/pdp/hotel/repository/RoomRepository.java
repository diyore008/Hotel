package uz.pdp.hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotel.entitiy.Room;
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Page<Room> findRoomByHotel_Id(Integer hotel_id, Pageable pageable);
}
