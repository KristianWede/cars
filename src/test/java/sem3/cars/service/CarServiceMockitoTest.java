package sem3.cars.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import sem3.cars.dto.CarRequest;
import sem3.cars.dto.CarResponse;
import sem3.cars.dto.MemberRequest;
import sem3.cars.dto.MemberResponse;
import sem3.cars.entity.Car;
import sem3.cars.entity.Member;
import sem3.cars.repositories.CarRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {

    @Mock
    CarRepository carRepository;
    CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService(carRepository);
    }

    @Test
    void findCarById() {
        Car car1 = new Car("Volvo","BingerBanger",200.0,100);

        Mockito.when(carRepository.findCarById(1)).thenReturn(car1);

        assertEquals(carRepository.findCarById(1),car1);
    }

    @Test
    void getCars() {
        Car car1 = new Car("Volvo","BingerBanger",200.0,100);
        Car car2 = new Car("Toyota","BingerBanger2",250,5000);

        car1.setCreated(LocalDateTime.now());
        car2.setCreated(LocalDateTime.now());

        Mockito.when(carRepository.findAll()).thenReturn(List.of(car1,car2));
        List<CarResponse> cars = carService.getCars(true);
        assertEquals(2,cars.size());

    }

    @Test
    void addCar() {

        Car car1 = new Car("Volvo","BingerBanger",200.0,100);
        //If Id was generated by the server, for example as for car you would need to set the id here
        Mockito.when(carRepository.save(any(Car.class))).thenReturn(car1);

        //Quick way to get a MemberRequest (remember eventually values come via a incoming JSON object)
        CarRequest request = new CarRequest(car1);

        CarResponse response = carService.addCar(request);
        assertEquals("Volvo",response.getBrand());

    }

    @Test
    void updateCar() {

        Car car1 = new Car("Volvo","BingerBanger",200.0,100);

        car1.setId(10);

        assertEquals(10,car1.getId());
    }

    @Test
    void editCarDiscount() {

        Car car1 = new Car("Volvo","BingerBanger",200.0,100);

        car1.setBestDiscount(10);

        assertEquals(10,car1.getBestDiscount());

    }

    @Test
    void deleteCar() {
        Car car1 = new Car("Volvo","BingerBanger",200.0,100);
        //Mockito.when(carRepository.save(any(Car.class))).thenReturn(car1);

        carRepository.save(car1);

        carService.deleteCar(car1.getId());

        assertEquals(0,carRepository.findAll().size());

    }
}