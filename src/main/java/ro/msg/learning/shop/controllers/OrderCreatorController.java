package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.model.OrderInput;
import ro.msg.learning.shop.services.OrderCreator;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 * Create order will be a POST mapping, JSON body and JSON response.
 */
@RestController
public class OrderCreatorController {

    private final OrderCreator orderCreator;

    @Autowired
    public OrderCreatorController(OrderCreator orderCreator) {
        this.orderCreator = orderCreator;
    }

    @PostMapping(value = "rest/orderCreator")
    public Order createOrder(@RequestBody OrderInput orderInput) {
        return orderCreator.createOrder(orderInput);
    }
}
