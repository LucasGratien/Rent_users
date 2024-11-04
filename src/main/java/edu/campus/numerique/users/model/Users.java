package edu.campus.numerique.users.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.Period;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
// Grace à lombok je peux déclarer mes guetters setters et constructeur de cette maniére.
@Entity
public class Users {
    @Id
   @GeneratedValue
    private int id;
    private String firstname;
    private String lastname;
    private LocalDate dateofbirth;
    private String numlicense;
    private LocalDate datelicense;

}

