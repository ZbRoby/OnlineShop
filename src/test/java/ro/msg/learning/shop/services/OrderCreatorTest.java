package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.exceptions.QuantityExceedsStockException;
import ro.msg.learning.shop.models.OrderInput;
import ro.msg.learning.shop.repositories.*;
import ro.msg.learning.shop.services.strategies.QuantityStrategy;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class OrderCreatorTest {

    @InjectMocks
    private OrderCreator orderCreator;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private CustomerRepository mockCustomerRepository;

    @Mock
    private AddressRepository mockAddressRepository;

    @Mock
    private OrderRepository mockOrderRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private QuantityStrategy quantityStrategy;


    private List<ProductsLocations> productsLocationsList;

    private void setUpProductsLocationsList() {
        productsLocationsList = new ArrayList<>();
        productsLocationsList.add(new ProductsLocations(10L, 10L, 1L));
        productsLocationsList.add(new ProductsLocations(17L, 10L, 1L));
        productsLocationsList.add(new ProductsLocations(12L, 11L, 3L));
        productsLocationsList.add(new ProductsLocations(20L, 13L, 1L));
        productsLocationsList.add(new ProductsLocations(10L, 13L, 2L));
        productsLocationsList.add(new ProductsLocations(15L, 14L, 2L));
        productsLocationsList.add(new ProductsLocations(18L, 17L, 6L));
        productsLocationsList.add(new ProductsLocations(11L, 17L, 1L));
        productsLocationsList.add(new ProductsLocations(14L, 18L, 2L));
        productsLocationsList.add(new ProductsLocations(13L, 18L, 1L));
        productsLocationsList.add(new ProductsLocations(16L, 20L, 2L));
        productsLocationsList.add(new ProductsLocations(16L, 20L, 1L));
        productsLocationsList.add(new ProductsLocations(12L, 20L, 2L));
    }

    private void setUpMockProductRepository() {
        when(mockProductRepository.findAllProductsLocationsInSet(Mockito.anySetOf(Long.class))).
            thenAnswer(
                (Answer<List<ProductsLocations>>) invocation ->
                    productsLocationsList.stream().filter(x -> ((Set) invocation.getArguments()[0]).
                        contains(x.getProductId())).collect(Collectors.toList())
            );
        when(mockProductRepository.findOne(anyLong())).thenAnswer(
            (Answer<Product>) invocation -> {
                if (productsLocationsList.stream().anyMatch(x -> x.getProductId().equals(invocation.getArguments()[0]))) {
                    Product p = new Product();
                    p.setId((Long) invocation.getArguments()[0]);
                    return p;
                } else {
                    return null;
                }
            }
        );
        when(mockProductRepository.getQuantityForProduct(anyLong())).thenAnswer(
            (Answer<Long>) invocation -> productsLocationsList.stream().
                filter(x -> x.getProductId().equals(invocation.getArguments()[0])).
                mapToLong(ProductsLocations::getQuantity).sum()
        );
    }

    private void setUpMockCustomerRepository() {
        when(mockCustomerRepository.findOptionalByUserUsername(anyString())).thenReturn(Optional.of(new Customer()));
        when(mockCustomerRepository.findAll()).then(invocation -> {
            ArrayList<Customer> ret = new ArrayList<>();
            ret.add(null);
            return ret;
        });
    }

    private void setUpMockOrderRepository() {
        when(mockOrderRepository.save(Mockito.any(Order.class))).thenAnswer(
            (Answer<Order>) invocation -> ((Order) invocation.getArguments()[0])
        );
    }

    private void setUpMockAddressRepository() {
        when(mockAddressRepository.save(Mockito.any(Address.class))).thenAnswer(
            (Answer<Address>) invocation -> ((Address) invocation.getArguments()[0])
        );
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpProductsLocationsList();
        setUpMockProductRepository();
        setUpMockCustomerRepository();
        setUpMockOrderRepository();
        setUpMockAddressRepository();
    }

    @Test
    public void emptyProductMapTest() {
        Address address = new Address("country", "city", "street", "zipCode", "other");
        OrderInput orderInput = new OrderInput(new HashMap<>(), null, address);
        SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("1", new User("name", "password", AuthorityUtils.createAuthorityList("CUSTOMER")), AuthorityUtils.createAuthorityList("CUSTOMER")));
        Order actual = orderCreator.createOrder(orderInput);
        Assert.assertEquals(0, actual.getOrdersDetails().size());
    }

    @Test
    public void enoughStockTest() {
        Address address = new Address("country", "city", "street", "zipCode", "other");
        OrderInput orderInput = new OrderInput();
        orderInput.setAddress(address);
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(16L, 3L);
        hashMap.put(10L, 1L);
        hashMap.put(17L, 1L);
        orderInput.setProductMap(hashMap);
        SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("1", new User("name", "password", AuthorityUtils.createAuthorityList("CUSTOMER")), AuthorityUtils.createAuthorityList("CUSTOMER")));
        Order actual = orderCreator.createOrder(orderInput);
        Assert.assertEquals(3, actual.getOrdersDetails().size());
    }

    @Test(expected = QuantityExceedsStockException.class)
    public void notEnoughStockTest() {
        Address address = new Address("country", "city", "street", "zipCode", "other");
        OrderInput orderInput = new OrderInput();
        orderInput.setAddress(address);
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(16L, 100L);
        orderInput.setProductMap(hashMap);
        orderCreator.createOrder(orderInput);
    }

    @Test(expected = ProductNotFoundException.class)
    public void noProductTest() {
        Address address = new Address("country", "city", "street", "zipCode", "other");
        OrderInput orderInput = new OrderInput();
        orderInput.setAddress(address);
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(100L, 1L);
        orderInput.setProductMap(hashMap);
        orderCreator.createOrder(orderInput);
    }
}
