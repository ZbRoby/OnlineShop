package ro.msg.learning.shop.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.local.util.TokenGetter;
import ro.msg.learning.shop.repositories.RoleRepository;
import ro.msg.learning.shop.repositories.UserRepository;

import java.util.ArrayList;

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

    private String token;

    @Before
    public void setUp() throws Exception {
        TokenGetter tokenGetter = new TokenGetter(testRestTemplate);
        token = tokenGetter.getAdminToken();
    }

    @Test
    public void elevateTest() {
        User u = new User("test", "test", new ArrayList<>());
        u = userRepository.save(u);
        testRestTemplate.put("/rest/elevate/" + u.getId() + "?access_token=" + token, null);
        Assert.assertTrue("Elevate", userRepository.findRolesNameByUserId(u.getId()).contains(roleRepository.findByName("ADMIN").getName()));
        userRepository.delete(u.getId());
    }
}
