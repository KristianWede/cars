package sem3.cars.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import sem3.cars.entity.Car;
import sem3.cars.entity.Member;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {

    private int id;
    private String brand;
    private String model;
    private double pricePrDay;
    private int bestDiscount;
    private LocalDateTime created;
    private LocalDateTime lastEdited;

    public static Car getCarEntity(CarRequest c){
        return new Car(c.id, c.brand,c.model,c.pricePrDay,c.bestDiscount,c.created,c.lastEdited);
    }


    public CarRequest(Car c) {
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
        this.created = c.getCreated();
        this.lastEdited = c.getLastEdited();
    }

}
