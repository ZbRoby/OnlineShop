package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.repositories.UserRepository;

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

    @Test
    public void elevateTest() {
        for (User user : userRepository.findAll()) {
            if (!userRepository.findRolesNameByUserId(user.getId()).contains("ADMIN")) {
                elevate.elevate(user.getId());
            }
        }
        for (User user : userRepository.findAll()) {
            Assert.assertTrue(userRepository.findRolesNameByUserId(user.getId()).contains("ADMIN"));
        }
    }
}
