package sem3.cars.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import sem3.cars.entity.Car;
import sem3.cars.entity.Member;
import sem3.cars.repositories.CarRepository;
import sem3.cars.repositories.MemberRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class DeveloperData implements ApplicationRunner {

    final CarRepository carRepository;
    final MemberRepository memberRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }


    private final String passwordUsedByAll = "Test123";
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("I was running");

        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota","BMX",500.0,400));
        cars.add(new Car("BMW","Boop",9000.0,400));
        cars.add(new Car("AUDI","XMX",800.0,400));
        cars.add(new Car("Tesla","GTX",10.0,9000000));

        carRepository.saveAll(cars);

            Member m1 = new Member("member1", passwordUsedByAll, "memb1@a.dk", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
            Member m2 = new Member("member2", passwordUsedByAll, "aaa@dd.dk", "Hanne", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");

            m1.setFavoriteCarColors(Arrays.asList("Black","Yellow"));
            m2.setFavoriteCarColors(Arrays.asList("Black","Yellow"));

            m1.setPhones(Map.of("mobile","12345","work", "45678"));
            m2.setPhones(Map.of("mobile", "91011","work", "49725"));


            memberRepository.save(m1);
            memberRepository.save(m2);
        }

    }
