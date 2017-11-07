package ro.msg.learning.shop.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class OrderDetailsRepositoryTest {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findAllByOrderTest() {
        orderRepository.findAll().forEach(x -> orderDetailsRepository.findAllByOrder(x).forEach(Assert::assertNotNull));
        assertTrue("Not empty", orderRepository.findAll().size() > 0);
    }
}
