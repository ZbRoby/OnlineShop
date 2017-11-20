package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Employee;
import ro.msg.learning.shop.entities.User;

import java.util.Optional;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findOptionalByUser(User user);

    Optional<Employee> findOptionalByUserUsername(String username);

    Optional<Employee> findOptionalByUserId(long id);
}
