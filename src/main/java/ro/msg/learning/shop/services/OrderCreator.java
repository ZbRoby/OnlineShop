package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.exceptions.QuantityExceedsStockException;
import ro.msg.learning.shop.model.OrderInput;
import ro.msg.learning.shop.repositories.*;
import ro.msg.learning.shop.services.strategies.QuantityStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Service
public class OrderCreator {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final QuantityStrategy quantityStrategy;


    @Autowired
    public OrderCreator(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, AddressRepository addressRepository, QuantityStrategy quantityStrategy) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.quantityStrategy = quantityStrategy;
    }

    private ArrayList<OrderDetails> getOrderDetails(OrderInput orderInput) {
        ArrayList<OrderDetails> orderDetailsList = new ArrayList<>();
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
        return orderDetailsList;
    }

    private Address getAddress(OrderInput orderInput) {
        Address address = addressRepository.findByCountryAndCityAndStreet(orderInput.getAddress().getCountry(), orderInput.getAddress().getCity(), orderInput.getAddress().getStreet());
        if (address == null) {
            address = addressRepository.save(orderInput.getAddress());
        }
        return address;
    }

    @Transactional
    public Order createOrder(OrderInput orderInput) {

        Order order = new Order();
        for (OrderDetails orderDetail : getOrderDetails(orderInput)) {
            order.addOrderDetail(orderDetail);
        }
        order.setOrderDate(orderInput.getDate());
        order.setAddress(getAddress(orderInput));
        order.setCustomer(customerRepository.findAll().get(0));//TODO add customer by getting the logged customer
        order.setEmployee(employeeRepository.getOne(orderRepository.employeeIdWithLeastOrders()));

        List<ProductsLocations> locations = quantityStrategy.
            getLocations(orderInput.getProductMap(), productRepository.
                findAllProductsLocationsInSet(orderInput.getProductMap().keySet()));

        locations.forEach(x -> productRepository.changeTheQuantity(x.getLocationId(), x.getProductId(), x.getQuantity()));

        return orderRepository.save(order);
    }
}

