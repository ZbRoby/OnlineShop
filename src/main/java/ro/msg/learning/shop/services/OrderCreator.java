package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.exceptions.NoCustomerException;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.exceptions.QuantityExceedsStockException;
import ro.msg.learning.shop.models.OrderInput;
import ro.msg.learning.shop.repositories.*;
import ro.msg.learning.shop.services.strategies.QuantityStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final LocationRepository locationRepository;


    @Autowired
    public OrderCreator(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, AddressRepository addressRepository, QuantityStrategy quantityStrategy, LocationRepository locationRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.quantityStrategy = quantityStrategy;
        this.locationRepository = locationRepository;
    }

    private void validateOrder(OrderInput orderInput) {
        for (Long key : orderInput.getProductMap().keySet()) {
            long tempLong = productRepository.getQuantityForProduct(key);
            if (productRepository.findOne(key) == null) {
                throw new ProductNotFoundException();
            } else if (tempLong < orderInput.getProductMap().get(key)) {
                throw new QuantityExceedsStockException(key, orderInput.getProductMap().get(key), tempLong);
            }
        }
    }

    private ArrayList<OrderDetails> getOrderDetails(List<ProductsLocations> productsLocations) {
        ArrayList<OrderDetails> orderDetailsList = new ArrayList<>();
        for (ProductsLocations productsLocation : productsLocations) {
            orderDetailsList.add(new OrderDetails(productsLocation.getQuantity(), 0.0, productsLocation.getProduct(), productsLocation.getLocation()));
        }
        return orderDetailsList;
    }

    private Address getAddress(OrderInput orderInput) {
        if (orderInput.getAddress() == null) {
            return getCustomerAddress();
        } else {
            Address address = addressRepository.findByCountryAndCityAndStreet(orderInput.getAddress().getCountry(), orderInput.getAddress().getCity(), orderInput.getAddress().getStreet());
            if (address == null) {
                address = addressRepository.save(orderInput.getAddress());
            }
            return address;
        }
    }

    private Address getCustomerAddress() {
        Optional<Customer> customerOptional;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            customerOptional = customerRepository.findOptionalByUserUsername(authentication.getName());
        } else {
            customerOptional = Optional.empty();
        }
        if (customerOptional.isPresent()) {
            return customerOptional.get().getAddress();
        } else {
            throw new NoCustomerException();
        }
    }

    private void completeTheLocations(List<ProductsLocations> locations) {
        List<Location> tempLocations = locationRepository.findAllByIdIn(locations.stream().map(ProductsLocations::getLocationId).collect(Collectors.toSet()));
        locations.forEach(x -> x.setLocation(tempLocations.stream().filter(y -> x.getLocationId().equals(y.getId())).findFirst().orElse(null)));

        List<Product> tempProducts = productRepository.findAllByIdIn(locations.stream().map(ProductsLocations::getProductId).collect(Collectors.toSet()));
        locations.forEach(x -> x.setProduct(tempProducts.stream().filter(y -> x.getProductId().equals(y.getId())).findFirst().orElse(null)));
    }

    private Customer getCustomer() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Customer> customerOptional = customerRepository.findOptionalByUserUsername(username);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            Optional<Employee> employeeOptional = employeeRepository.findOptionalByUserUsername(username);
            if (employeeOptional.isPresent()) {
                Customer customer = new Customer();
                customer.setLastName(employeeOptional.get().getLastName());
                customer.setFirstName(employeeOptional.get().getFirstName());
                customer.setUser(employeeOptional.get().getUser());
                customer.setAddress(employeeOptional.get().getAddress());
                customer = customerRepository.save(customer);
                return customer;
            } else {
                throw new UsernameNotFoundException("could not find the user '" + username + "'");
            }
        }
    }

    @Transactional
    @PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN')")
    public Order createOrder(OrderInput orderInput) {
        validateOrder(orderInput);

        List<ProductsLocations> locations = quantityStrategy.getLocations(orderInput.getProductMap(), productRepository.findAllProductsLocationsInSet(orderInput.getProductMap().keySet()));
        completeTheLocations(locations);
        locations.forEach(x -> productRepository.changeTheQuantity(x.getLocationId(), x.getProductId(), x.getQuantity()));

        Order order = new Order();
        for (OrderDetails orderDetail : getOrderDetails(locations)) {
            order.addOrderDetail(orderDetail);
        }
        order.setOrderDate(orderInput.getDate());
        order.setAddress(getAddress(orderInput));
        order.setCustomer(getCustomer());
        order.setEmployee(employeeRepository.getOne(orderRepository.employeeIdWithLeastOrders()));


        return orderRepository.save(order);
    }
}

