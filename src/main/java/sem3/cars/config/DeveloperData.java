package sem3.cars.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import sem3.cars.entity.Car;
import sem3.cars.entity.Member;
import sem3.cars.entity.Reservation;
import sem3.cars.repositories.CarRepository;
import sem3.cars.repositories.MemberRepository;
import sem3.cars.repositories.ReservationRepository;
import sem3.security.entity.Role;
import sem3.security.entity.UserWithRoles;
import sem3.security.repository.UserWithRolesRepository;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class DeveloperData implements ApplicationRunner {


    final CarRepository carRepository;
    final MemberRepository memberRepository;
    final ReservationRepository reservationRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository, UserWithRolesRepository userWithRolesRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
        this.userWithRolesRepository = userWithRolesRepository;
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

            Map<String,String> phoneNumbers = new HashMap<>();

            //Week 3 Exercise.
        /*

        Car car = new Car("Toyota","BMX",500.0,400);
        Member mem = new Member("member5", passwordUsedByAll, "memb1@a.dk", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");

        Reservation reservation = new Reservation(LocalDateTime.now(),mem,car);

        //reservationRepository.save(reservation);


         */


        setupUserWithRoleUsers();


        }

    UserWithRolesRepository userWithRolesRepository;

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/


    private void setupUserWithRoleUsers() {

        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }


}
