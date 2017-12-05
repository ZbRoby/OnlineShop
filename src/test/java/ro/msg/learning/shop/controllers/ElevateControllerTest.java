package ro.msg.learning.shop.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Role;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.repositories.RoleRepository;
import ro.msg.learning.shop.repositories.UserRepository;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ElevateControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private String getToken() {
        try {
            String token = testRestTemplate.withBasicAuth("android", "123456")
                .postForObject("/oauth/token?" +
                    "grant_type=" + "password" +
                    "&username=" + "admin" +
                    "&password=" + "test" +
                    "", null, String.class);
            return token.split("\"")[3];
        } catch (Exception e) {
            e.printStackTrace();
            return "ElevateControllerTestError";
        }
    }

    @Test
    public void elevateTest() {
        Role admin = roleRepository.findByName("ADMIN");
        long id = userRepository.findAll().stream().filter(x -> !x.getRoles().contains(admin)).findFirst().orElse(new User()).getId();
        if (id != 0) {
            testRestTemplate.put("/rest/elevate/" + id + "?access_token=" + getToken(), null);
            Assert.assertTrue("Elevate", userRepository.findOne(id).getRoles().contains(admin));
        }
    }
}
