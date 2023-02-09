package sem3.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sem3.cars.dto.MemberRequest;
import sem3.cars.dto.MemberResponse;
import sem3.cars.entity.Car;
import sem3.cars.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    boolean existsByEmail(String email);

    Member findMemberByUsername(String username);

}