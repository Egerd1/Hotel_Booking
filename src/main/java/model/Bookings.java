package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date arrivalDate;
    private Date leaveDate;

    @ManyToOne
    private Client client;

    @OneToOne
    private Hotel hotel;
    private Double totalAmount;
}
