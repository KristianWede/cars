package sem3.cars.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.cars.repositories.CarRepository;
import sem3.cars.service.CarService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarControllerMockitoTest {

    @Mock
    CarRepository carRepository;
    CarController carController;

    @BeforeEach
    void setUp() {
       // carController = new CarController(carRepository);
    }

    @Test
    void getCars() {

    }

    @Test
    void getCarById() {
    }

    @Test
    void addCar() {
    }

    @Test
    void editCar() {
    }

    @Test
    void setDiscountForCar() {
    }

    @Test
    void deleteCarById() {
    }
}