package ro.msg.learning.shop.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderDetailsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findAllByOrderTest() {
        orderRepository.findAll().forEach(x -> orderDetailsRepository.findAllByOrder(x).forEach(Assert::assertNotNull));

    }
}
