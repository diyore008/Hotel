package uz.pdp.hotel.controller;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.entitiy.Hotel;
import uz.pdp.hotel.entitiy.Room;
import uz.pdp.hotel.payload.RoomDto;
import uz.pdp.hotel.repository.HotelRepository;
import uz.pdp.hotel.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public List<Room> getRoom(){
        List<Room> allRoom = roomRepository.findAll();
        return allRoom;
    }

    @PostMapping("/addRoom")
    public String addRoom(@RequestBody RoomDto roomDto){
        Optional<Hotel> findHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!findHotel.isPresent())
            return "Hotel not found";
        Room addRoom = new Room();
        addRoom.setNumber(roomDto.getNumber());
        addRoom.setFloor(roomDto.getFloor());
        addRoom.setSize(roomDto.getSize());
        addRoom.setHotel(findHotel.get());
        roomRepository.save(addRoom);
        return "Saved room";
    }

    @PutMapping("updateRoom/{id}")
    public String updateRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto){
        Optional<Room> findRoom = roomRepository.findById(id);
        if (!findRoom.isPresent())
            return "Room not found";
        Optional<Hotel> findHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!findHotel.isPresent())
            return "Hotel not found";
        Room updateRoom = findRoom.get();
        updateRoom.setNumber(roomDto.getNumber());
        updateRoom.setFloor(roomDto.getFloor());
        updateRoom.setSize(roomDto.getSize());
        updateRoom.setHotel(findHotel.get());
        roomRepository.save(updateRoom);
        return "Updated room";

    }

    @DeleteMapping("deleteRoom/{id}")
    public String deleteRoom(@PathVariable Integer id){
        Optional<Room> findRoom  = roomRepository.findById(id);
        if (findRoom.isPresent()){
            roomRepository.deleteById(id);
            return "Deleted room";
        }
        return "Room not found";
    }
}
