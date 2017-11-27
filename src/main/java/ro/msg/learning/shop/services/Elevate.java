package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Role;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.repositories.RoleRepository;
import ro.msg.learning.shop.repositories.UserRepository;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@Service
public class Elevate {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public Elevate(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void elevate(long id) {
        User user = userRepository.findOne(id);
        Role role = roleRepository.findByName("ADMIN");
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
        }
        userRepository.save(user);
    }
}
