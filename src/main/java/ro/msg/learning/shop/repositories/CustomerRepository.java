package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.entities.User;

import java.util.Optional;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findOptionalByUser(User user);

    Optional<Customer> findOptionalByUserUsername(String username);

    Optional<Customer> findOptionalByUserId(long id);
}
