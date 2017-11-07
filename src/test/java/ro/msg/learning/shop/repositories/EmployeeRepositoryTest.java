package ro.msg.learning.shop.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Employee;
import ro.msg.learning.shop.entities.enums.Title;

import static org.junit.Assert.*;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void findByUsernameTest() {
        String username1 = "Username1";
        String username2 = "Username2";
        String username3 = "UsernameNone";
        long count = repository.count();

        entityManager.persist(new Employee("test1", "test1", username1, "test1", "test1", Title.INTERN));
        repository.save(new Employee("test2", "test2", username2, "test2", "test2", Title.MANAGER));

        assertNotNull("Test Employee byUsername NotNull persist", repository.findByUsername(username1));
        assertNotNull("Test Employee byUsername NotNull save", repository.findByUsername(username2));
        assertNull("Test Employee byUsername Null", repository.findByUsername(username3));

        repository.delete(repository.findByUsername(username1));
        repository.delete(repository.findByUsername(username2));

        assertTrue("Same nr of addresses; expect:" + count + " got:" + repository.count(), repository.count() == count);
    }
}
