package sem3.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sem3.cars.entity.Car;
import sem3.cars.entity.Member;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findCarById(int id);

}