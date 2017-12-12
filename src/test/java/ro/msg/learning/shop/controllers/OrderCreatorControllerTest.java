package ro.msg.learning.shop.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.local.util.TokenGetter;
import ro.msg.learning.shop.models.OrderInput;
import ro.msg.learning.shop.services.OrderCreator;
import ro.msg.learning.shop.services.strategies.QuantityStrategy;
import ro.msg.learning.shop.utils.DistanceCalculator;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderCreatorControllerTest {

    @Mock
    private DistanceCalculator distanceCalculator;

    @Autowired
    @InjectMocks
    private QuantityStrategy quantityStrategy;

    @Autowired
    private OrderCreator orderCreator;

    @InjectMocks
    private OrderCreatorController orderCreatorController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String token;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        TokenGetter tokenGetter = new TokenGetter(testRestTemplate);
        token = tokenGetter.getCustomerToken();
        Mockito.when(distanceCalculator.sortLocations(Mockito.any(Address.class), Mockito.anyListOf(ProductsLocations.class))).thenAnswer(
            answer -> answer.getArgumentAt(1, List.class)
        );
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
        ResponseEntity<Order> order = testRestTemplate.postForEntity("/rest/orderCreator?access_token=" + token, orderInput, Order.class);
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
        ResponseEntity<String> order = testRestTemplate.postForEntity("/rest/orderCreator?access_token=" + token, orderInput, String.class);
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
        ResponseEntity<String> order = testRestTemplate.postForEntity("/rest/orderCreator?access_token=" + token, orderInput, String.class);
        Assert.assertNotNull(order.getBody());
        Assert.assertTrue(order.getStatusCode().is4xxClientError());
    }
}
