package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.msg.learning.shop.entities.Role;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.repositories.RoleRepository;
import ro.msg.learning.shop.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class ElevateTest {

    @InjectMocks
    private Elevate elevate;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;


    private List<User> userList;
    private List<Role> roleList;
    private Role adminRole;

    private void setUpData() {
        roleList = new ArrayList<>();
        adminRole = new Role("ADMIN");
        Role customerRole = new Role("CUSTOMER");
        List<Role> adminList = new ArrayList<>();
        List<Role> customerList = new ArrayList<>();
        adminList.add(adminRole);
        customerList.add(customerRole);
        roleList.addAll(adminList);
        roleList.addAll(customerList);
        userList = new ArrayList<>();
        userList.add(new User("admin1", "test", adminList));
        userList.add(new User("customer1", "test", customerList));
        userList.add(new User("admin2", "test", adminList));
        userList.add(new User("customer2", "test", customerList));
        userList.add(new User("admin3", "test", adminList));
        userList.add(new User("customer3", "test", customerList));
        userList.add(new User("admin4", "test", adminList));
        userList.add(new User("customer4", "test", customerList));
    }

    private void setUpMockUserRepository() {
        Mockito.when(mockUserRepository.findOne(Mockito.anyLong())).thenAnswer(
            x -> userList.get(((Long) x.getArguments()[0]).intValue())
        );
    }

    private void setUpMockRoleRepository() {
        Mockito.when(mockRoleRepository.findByName(Mockito.anyString())).thenAnswer(
            x ->
                roleList.stream().filter(y -> y.getName().equals(x.getArguments()[0])).findFirst().get()
        );
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpData();
        setUpMockUserRepository();
        setUpMockRoleRepository();
    }

    @Test
    public void elevateTest() {
        for (int i = 0; i < userList.size(); i++) {
            elevate.elevate(i);
            Assert.assertTrue(userList.get(i).getRoles().contains(adminRole));
        }
    }
}

