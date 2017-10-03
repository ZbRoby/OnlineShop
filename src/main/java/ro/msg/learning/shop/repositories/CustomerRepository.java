package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Customer;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
