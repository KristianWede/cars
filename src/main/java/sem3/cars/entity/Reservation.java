package sem3.cars.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private LocalDateTime reservationDate;

    private LocalDateTime rentalDate;

    @ManyToOne
    Member member;
    @ManyToOne
    Car car;

    public Reservation(LocalDateTime rentalDate, Member member, Car car) {
        this.rentalDate = rentalDate;
        this.member = member;
        this.car = car;
    }
}
