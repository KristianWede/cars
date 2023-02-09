package sem3.cars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sem3.cars.entity.Car;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {

    private int id;
    private String brand;
    private String model;
    private double pricePrDay;
    private int bestDiscount;
    private LocalDateTime created;
    private LocalDateTime lastEdited;

    public CarResponse(Car car, boolean includeAll){
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePrDay = car.getPricePrDay();
        this.bestDiscount = car.getBestDiscount();
        if(includeAll) {
            this.created = car.getCreated();
            this.lastEdited = car.getLastEdited();
        }
    }

}
