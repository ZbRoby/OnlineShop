package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Role;

import java.util.Optional;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findOptionalByName(String name);

    Role findByName(String name);
}
