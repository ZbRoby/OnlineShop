package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Revenue;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}
