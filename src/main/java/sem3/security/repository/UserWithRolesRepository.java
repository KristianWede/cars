package sem3.security.repository;

import sem3.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import sem3.security.entity.UserWithRoles;


public interface UserWithRolesRepository extends JpaRepository<UserWithRoles,String> {
    Boolean existsByEmail(String email);
}
