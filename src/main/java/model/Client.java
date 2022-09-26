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
@Entity(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long personalId;
    private String firstName;
    private String lastName;
    private String age;

    @Override
    public String toString() {
        return
                "PersonalId= " + personalId +
                        "First Name= " + firstName +
                        "Last Name= " + lastName +
                        "Age= " + age + '\n'
                ;
    }
}
