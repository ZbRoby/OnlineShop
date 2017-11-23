package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.User;

import java.util.Optional;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOptionalByUsername(String username);
}
