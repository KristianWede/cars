package sem3.cars.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sem3.cars.dto.CarRequest;
import sem3.cars.dto.CarResponse;
import sem3.cars.dto.MemberRequest;
import sem3.cars.dto.MemberResponse;
import sem3.cars.entity.Car;
import sem3.cars.entity.Member;
import sem3.cars.repositories.CarRepository;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public CarResponse findCarById(int id) {

        CarResponse carResponse = new CarResponse(carRepository.findCarById(id),true);
        return carResponse;
    }

    public List<CarResponse> getCars(boolean includeAll) {
        List<Car> cars = carRepository.findAll();

        List<CarResponse> carResponses = cars.stream().map(m->new CarResponse(m,includeAll)).toList();

        return carResponses;
    }


    public CarResponse addCar(CarRequest carRequest) {

/*        if(carRepository.existsById(carRequest.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"A Car with this ID already exist");
        }*/
        //Later you should add error checks --> Missing arguments, email taken etc.

        Car newCar = CarRequest.getCarEntity(carRequest);
        newCar = carRepository.save(newCar);

        return new CarResponse(newCar, false);

    }

    public ResponseEntity<Boolean> updateCar(CarRequest body, int id) {
            Car mtoUpdate = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            //mtoUpdate.setId(body.getId());

            return ResponseEntity.ok(true);
    }

    public void editCarDiscount(int id, int value) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        car.setBestDiscount(value);
        carRepository.save(car);
    }

    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }
}
