package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate arrivalDate;
    private LocalDate leaveDate;

    @ManyToOne
    private Client client;

    @OneToOne
    private Hotel hotel;
    private Double totalAmount;

    @Override
    public String toString() {
        return
                "id= " + id +
                        " arrivalDate= " + arrivalDate +
                        " leaveDate= " + leaveDate +
                        " client= " + client +
                        " hotel= " + hotel +
                        " totalAmount= " + totalAmount +"\n"
                ;
    }

}
