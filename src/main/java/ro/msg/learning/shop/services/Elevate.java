package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Role;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.repositories.RoleRepository;
import ro.msg.learning.shop.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        Role adminRole = roleRepository.findByName("ADMIN");
        List<Role> roleList = userRepository.findRolesNameByUserId(id).stream().map(roleRepository::findByName).collect(Collectors.toList());
        if (!roleList.contains(adminRole)) {
            roleList.add(adminRole);
            user.setRoles(roleList);
        }
        userRepository.save(user);
    }
}
