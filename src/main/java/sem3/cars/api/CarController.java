package sem3.cars.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.cars.dto.CarRequest;
import sem3.cars.dto.CarResponse;
import sem3.cars.dto.MemberRequest;
import sem3.cars.dto.MemberResponse;
import sem3.cars.service.CarService;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping
    List<CarResponse> getCars(){
        return carService.getCars(false);
    }

    //Security? ADMIN and USER ??
    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable int id) throws Exception {
        return carService.findCarById(id);
    }

    //Security? ANONYMOUS
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //Security? MEMBER
    @PutMapping("/{id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
        carService.updateCar(body,id);
        return ResponseEntity.ok(true);
    }

    //Security? ADMIN
    @PatchMapping("/bestdiscount/{id}/{value}")
    void setDiscountForCar(@PathVariable int id, @PathVariable int value) {

        carService.editCarDiscount(id,value);

    }

    // Security? ADMIN
    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable int id) {
        carService.deleteCar(id);
    }


}
