package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.exceptions.QuantityExceedsStockException;
import ro.msg.learning.shop.model.OrderInput;

import java.sql.Date;
import java.util.HashMap;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderCreatorIntegrationTest {

    @Autowired
    private OrderCreator orderCreator;

    @Test
    public void emptyProductMapTest() {
        Address address = new Address("country", "city", "street", "zipCode", "other");
        Date date = new Date(125465463);
        OrderInput orderInput = new OrderInput(new HashMap<>(), date, address);
        Order actual = orderCreator.createOrder(orderInput);
        Assert.assertEquals(0, actual.getOrdersDetails().size());
    }

    @Test
    public void enoughStockTest() {
        Address address = new Address("country", "city", "street", "zipCode", "other");
        Date date = new Date(125465463);
        OrderInput orderInput = new OrderInput();
        orderInput.setAddress(address);
        orderInput.setDate(date);
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(1L, 7L);
        hashMap.put(6L, 5L);
        orderInput.setProductMap(hashMap);
        Order actual = orderCreator.createOrder(orderInput);
        Assert.assertEquals(2, actual.getOrdersDetails().size());
    }

    @Test(expected = QuantityExceedsStockException.class)
    public void notEnoughStockTest() {
        Address address = new Address("country", "city", "street", "zipCode", "other");
        OrderInput orderInput = new OrderInput();
        orderInput.setAddress(address);
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(16L, 1000L);
        orderInput.setProductMap(hashMap);
        orderCreator.createOrder(orderInput);
    }

    @Test(expected = ProductNotFoundException.class)
    public void noProductTest() {
        Address address = new Address("country", "city", "street", "zipCode", "other");
        OrderInput orderInput = new OrderInput();
        orderInput.setAddress(address);
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(1000L, 1L);
        orderInput.setProductMap(hashMap);
        orderCreator.createOrder(orderInput);
    }
}
