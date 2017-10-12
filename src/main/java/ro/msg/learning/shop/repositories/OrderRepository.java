package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Order;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "Select EMPLOYEE_ID FROM ORDERS GROUP by EMPLOYEE_ID ORDER BY count(ID) ASC LIMIT 1", nativeQuery = true)
    Long employeeIdWithLeastOrders();

    @Query(value = "Select EMPLOYEE_ID FROM ORDERS GROUP by EMPLOYEE_ID ORDER BY count(ID) DESC LIMIT 1", nativeQuery = true)
    Long employeeIdWithMostOrders();

    Long countByEmployeeId(Long id);
}
