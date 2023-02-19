package sem3.cars.service;

import org.springframework.stereotype.Service;
import sem3.cars.dto.MemberResponse;
import sem3.cars.entity.Car;
import sem3.cars.entity.Member;
import sem3.cars.entity.Reservation;
import sem3.cars.repositories.CarRepository;
import sem3.cars.repositories.ReservationRepository;

import java.time.LocalDateTime;

@Service
public class ReservationService {


    ReservationRepository reservationRepository;
    CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
    }

    public void makeReservation(Member member, int carId, LocalDateTime rentalDate){
        Car car = carRepository.findCarById(carId);
        Reservation reservation = new Reservation(rentalDate,member,car);
        reservationRepository.save(reservation);
    }

}
