package ro.msg.learning.shop.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Customer;

import static org.junit.Assert.*;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class CustomerRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository repository;

    @Test
    public void findByUsernameTest() {
        String username1 = "Username1";
        String username2 = "Username2";
        String username3 = "UsernameNone";
        long count = repository.count();

        entityManager.persist(new Customer("test1", "test1", username1, "test1"));
        repository.save(new Customer("test2", "test2", username2, "test2"));

        assertNotNull("Test Customer byUsername NotNull persist", repository.findByUsername(username1));
        assertNotNull("Test Customer byUsername NotNull save", repository.findByUsername(username2));
        assertNull("Test Customer byUsername Null", repository.findByUsername(username3));

        repository.delete(repository.findByUsername(username1));
        repository.delete(repository.findByUsername(username2));

        assertTrue("Same nr of addresses; expect:" + count + " got:" + repository.count(), repository.count() == count);
    }
}
