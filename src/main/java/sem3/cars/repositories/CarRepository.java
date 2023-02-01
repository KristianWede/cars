package sem3.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sem3.cars.entity.Car;

public interface CarRepository extends JpaRepository<Car, String> {

}