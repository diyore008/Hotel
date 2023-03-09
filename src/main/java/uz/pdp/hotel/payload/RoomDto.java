package uz.pdp.hotel.payload;

import lombok.Data;

@Data
public class RoomDto {
    private long number;
    private int floor;
    private int size;
    private Integer hotelId;
}
