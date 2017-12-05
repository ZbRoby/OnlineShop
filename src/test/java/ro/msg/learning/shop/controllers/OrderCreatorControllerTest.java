package ro.msg.learning.shop.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.models.OrderInput;

import java.sql.Date;
import java.util.HashMap;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderCreatorControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    public OrderCreatorControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    private String getToken() {
        try {
            String token = testRestTemplate.withBasicAuth("android", "123456")
                .postForObject("/oauth/token?" +
                    "grant_type=" + "password" +
                    "&username=" + "customer" +
                    "&password=" + "test" +
                    "", null, String.class);
            return token.split("\"")[3];
        } catch (Exception e) {
            e.printStackTrace();
            return "OrderCreatorControllerTestError";
        }
    }

    @Test
    public void createOrderTest() {
        OrderInput orderInput = new OrderInput();
        HashMap<Long, Long> productQuantity = new HashMap<>();
        productQuantity.put(1L, 1L);
        productQuantity.put(2L, 10L);
        productQuantity.put(17L, 8L);
        orderInput.setDate(Date.valueOf("1995-10-10"));
        orderInput.setAddress(new Address("C", "c", "s", "z", "o"));
        orderInput.setProductMap(productQuantity);
        ResponseEntity<Order> order = testRestTemplate.postForEntity("/rest/orderCreator?access_token=" + getToken(), orderInput, Order.class);
        Assert.assertNotEquals(0, order.getBody().getId());
        Assert.assertTrue(order.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void createOrderTestProductNotFound() {
        OrderInput orderInput = new OrderInput();
        HashMap<Long, Long> productQuantity = new HashMap<>();
        productQuantity.put(100L, 1L);
        orderInput.setDate(Date.valueOf("1995-10-10"));
        orderInput.setAddress(new Address("C", "c", "s", "z", "o"));
        orderInput.setProductMap(productQuantity);
        ResponseEntity<String> order = testRestTemplate.postForEntity("/rest/orderCreator?access_token=" + getToken(), orderInput, String.class);
        Assert.assertNotNull(order.getBody());
        Assert.assertTrue(order.getStatusCode().is4xxClientError());
    }

    @Test
    public void createOrderTestNotEnoughStock() {
        OrderInput orderInput = new OrderInput();
        HashMap<Long, Long> productQuantity = new HashMap<>();
        productQuantity.put(1L, 100000L);
        orderInput.setDate(Date.valueOf("1995-10-10"));
        orderInput.setAddress(new Address("C", "c", "s", "z", "o"));
        orderInput.setProductMap(productQuantity);
        ResponseEntity<String> order = testRestTemplate.postForEntity("/rest/orderCreator?access_token=" + getToken(), orderInput, String.class);
        Assert.assertNotNull(order.getBody());
        Assert.assertTrue(order.getStatusCode().is4xxClientError());
    }
}
