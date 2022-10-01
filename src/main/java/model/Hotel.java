package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hotelName;
    private String address;
    private int numberOfRooms = 30;
    private Double price;


    @Override
    public String toString() {
        return
                "*** Id= " + id +
                        "   HotelName= " + hotelName +
                        "   Address= " + address +
                        "   Number Of Rooms= " + numberOfRooms +
                        "   Price per day= " + price + '\n'
                ;
    }

}
