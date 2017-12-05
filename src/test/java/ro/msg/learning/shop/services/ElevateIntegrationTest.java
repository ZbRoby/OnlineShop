package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Role;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.repositories.RoleRepository;
import ro.msg.learning.shop.repositories.UserRepository;

import java.util.logging.Logger;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser(username = "admin", authorities = "ADMIN")
public class ElevateIntegrationTest {


    @Autowired
    private Elevate elevate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void elevateTest() {
        Role admin = roleRepository.findByName("ADMIN");
        for (User user : userRepository.findAll()) {
            if (!user.getRoles().contains(admin)) {
                elevate.elevate(user.getId());
            }
        }
        for (User user : userRepository.findAll()) {
            Logger.getGlobal().info(user.toString());
            Assert.assertTrue(user.getRoles().contains(admin));
        }
    }
}
