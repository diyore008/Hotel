package uz.pdp.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.entitiy.Hotel;
import uz.pdp.hotel.entitiy.Room;
import uz.pdp.hotel.repository.HotelRepository;
import uz.pdp.hotel.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @GetMapping()
    public List<Hotel> getHotel(){
        List<Hotel> allHotel = hotelRepository.findAll();
        return allHotel;
    }

    @GetMapping("/getByHotelIdRoom/{hotelId}")
    public Page<Room> getRoomByHotelId(@PathVariable Integer hotelId,
                                       @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = roomRepository.findRoomByHotel_Id(hotelId, pageable);
        return roomPage;
    }

    @PostMapping("/addHotel")
    public String addHotel(@RequestBody Hotel hotel){
        Hotel addHotel = new Hotel();
        addHotel.setName(hotel.getName());
        hotelRepository.save(addHotel);
        return "Saved hotel";
    }

    @PutMapping("/updateHotel/{id}")
    public String updateHotel(@PathVariable Integer id, @RequestBody Hotel hotel){
        Optional<Hotel> findHotel = hotelRepository.findById(id);
        if (!findHotel.isPresent())
            return "Hotel not found";
        Hotel updateHotel = findHotel.get();
        updateHotel.setName(hotel.getName());
        hotelRepository.save(updateHotel);
        return "Updated hotel";
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id){
        Optional<Hotel> findHotel = hotelRepository.findById(id);
        if (findHotel.isPresent()){
            hotelRepository.deleteById(id);
            return "Deleted hotel";
        }
        return "Hotel not found";
    }

}
