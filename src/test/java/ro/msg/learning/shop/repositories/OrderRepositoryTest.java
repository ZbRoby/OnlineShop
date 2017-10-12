package ro.msg.learning.shop.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    private Map<Long, Long> counts;
    private List<Long> emp;

    @Before
    public void setUp() throws Exception {
        List<Order> orders = repository.findAll();
        counts = new HashMap<>();
        emp = new ArrayList<>();
        for (Order order : orders) {
            if (counts.containsKey(order.getEmployee().getId())) {
                counts.put(order.getEmployee().getId(), counts.get(order.getEmployee().getId()) + 1);
            } else {
                counts.put(order.getEmployee().getId(), (long) 1);
                emp.add(order.getEmployee().getId());
            }
        }
    }

    @Test
    public void employeeIdWithLeastOrdersTest() {
        Long L = repository.employeeIdWithLeastOrders();
        emp.forEach(x -> Assert.assertTrue(counts.get(x) >= counts.get(L)));
        Assert.assertTrue("Not empty",emp.size()>0);
    }

    @Test
    public void employeeIdWithMostOrdersTest() {
        Long M = repository.employeeIdWithMostOrders();
        emp.forEach(x -> Assert.assertTrue(counts.get(x) <= counts.get(M)));
        Assert.assertTrue("Not empty",emp.size()>0);
    }

    @Test
    public void countByEmployee_IdTest() {
        emp.forEach(x -> Assert.assertTrue(counts.get(x).equals(repository.countByEmployee_Id(x))));
        Assert.assertTrue("Not empty",emp.size()>0);
    }
}
