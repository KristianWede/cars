package sem3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sem3.cars.entity.Car;
import sem3.cars.entity.Member;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {
    private LocalDateTime reservationDate;

    @JsonFormat(pattern= "yyyy-MM-dd")
    private LocalDateTime rentalDate;

    Member member;

    Car car;

}
