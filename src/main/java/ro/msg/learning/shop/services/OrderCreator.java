package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.exceptions.QuantityExceedsStockException;
import ro.msg.learning.shop.model.OrderInput;
import ro.msg.learning.shop.repositories.*;
import ro.msg.learning.shop.services.strategy.QuantityStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Service
public class OrderCreator {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;


    @Autowired
    public OrderCreator(OrderRepository orderRepository, ProductRepository productRepository, OrderDetailsRepository orderDetailsRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Order createOrder(OrderInput orderInput, QuantityStrategy quantityStrategy) {
        ArrayList<OrderDetails> orderDetailsList = new ArrayList<>();
        Order order = new Order();

        Product tempProduct;
        for (Long key : orderInput.getProductMap().keySet()) {
            tempProduct = productRepository.findOne(key);
            long tempLong = productRepository.getQuantityForProduct(key);
            if (tempProduct == null) {
                throw new ProductNotFoundException();
            } else if (tempLong < orderInput.getProductMap().get(key)) {
                throw new QuantityExceedsStockException(key, orderInput.getProductMap().get(key), tempLong);
            } else {
                orderDetailsList.add(new OrderDetails(orderInput.getProductMap().get(key), 0.0, tempProduct));
            }
        }
        order.setOrderDate(orderInput.getDate());
        Address address = addressRepository.findByCountryAndCityAndStreet(orderInput.getAddress().getCountry(), orderInput.getAddress().getCity(), orderInput.getAddress().getStreet());
        if (address == null) {
            address = addressRepository.save(orderInput.getAddress());
        }
        order.setAddress(address);
        order.setCustomer(customerRepository.findAll().get(0));//TODO add customer by getting the logged customer
        order.setEmployee(employeeRepository.getOne(orderRepository.employeeIdWithLeastOrders()));
        order = orderRepository.save(order);
        for (OrderDetails orderDetails : orderDetailsList) {
            orderDetails.setOrder(order);
            orderDetailsRepository.save(orderDetails);
        }

        List<ProductsLocations> productsLocationsList = productRepository.
            findAllProductsLocationsInSet(orderInput.getProductMap().keySet());

        List<ProductsLocations> locations = quantityStrategy.getLocations(orderInput.getProductMap(), productsLocationsList);
        Logger.getLogger(quantityStrategy.getClass().getName().split("\\.")[6]).log(Level.INFO, locations::toString);//TODO delete this when all of the strategies are done!

        //locations.forEach(x-> productRepository.changeTheQuantity(x.getElement1(),x.getElement2().getElement1(),x.getElement2().getElement2()));//TODO uncomment this when all of the strategies are done!

        return order;
    }
}

