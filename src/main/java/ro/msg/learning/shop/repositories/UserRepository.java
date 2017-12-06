package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOptionalByUsername(String username);

    @Query(value = "SELECT r.NAME FROM USER_ROLES r JOIN USERS_JOIN_ROLES j on r.ID = j.ROLE_ID where j.USER_ID=?1", nativeQuery = true)
    List<String> findRolesNameByUserId(Long id);

}
