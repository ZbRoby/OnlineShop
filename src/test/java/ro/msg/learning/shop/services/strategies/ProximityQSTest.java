package ro.msg.learning.shop.services.strategies;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.repositories.CustomerRepository;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

public class ProximityQSTest {

    @Mock
    private CustomerRepository customerRepository;

    private QuantityStrategy quantityStrategy;


    public ProximityQSTest() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(customerRepository.findOptionalByUserUsername(Mockito.anyString())).thenReturn(Optional.of(getCustomer()));
        quantityStrategy = new ProximityQS(customerRepository);
    }

    private Location getLocation(String city, String country) {
        Location location = new Location();
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        location.setAddress(address);
        return location;
    }

    private Customer getCustomer() {
        Customer c = new Customer();
        Address a = new Address();
        a.setCity("Ben Cau");
        a.setCountry("Vietnam");
        c.setAddress(a);
        return c;
    }

    private void addAddress(List<ProductsLocations> productsLocations) {
        for (ProductsLocations productsLocation : productsLocations) {
            if (productsLocation.getLocationId() == 7L) {
                productsLocation.setLocation(getLocation("Cibebek", "Indonesia"));
            }
            if (productsLocation.getLocationId() == 8L) {
                productsLocation.setLocation(getLocation("Rancabuaya", "Indonesia"));
            }
        }
    }

    @Test
    public void emptyProductMap() {
        Assert.assertTrue(quantityStrategy.getLocations(new HashMap<>(), new ArrayList<>()).isEmpty());
    }

    @Test(expected = InvalidParameterException.class)
    public void notEnoughQuantity() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(1L, 1L);
        quantityStrategy.getLocations(hashMap, new ArrayList<>());
    }

    @Test
    public void oneLocation() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(1L, 1L);
        hashMap.put(2L, 2L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        productsLocations.add(new ProductsLocations(1L, 7L, 1L));
        productsLocations.add(new ProductsLocations(2L, 7L, 2L));
        addAddress(productsLocations);

        Assert.assertEquals(productsLocations, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test
    public void twoLocationsEnoughInFirst() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(1L, 1L);
        hashMap.put(2L, 2L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        List<ProductsLocations> expected = new ArrayList<>();
        productsLocations.add(new ProductsLocations(1L, 7L, 1L));
        productsLocations.add(new ProductsLocations(2L, 7L, 2L));
        productsLocations.add(new ProductsLocations(1L, 8L, 1L));
        productsLocations.add(new ProductsLocations(2L, 8L, 2L));
        addAddress(productsLocations);
        expected.addAll(productsLocations.subList(0, 2));

        Assert.assertEquals(expected, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test
    public void twoLocationsNeedsBoth() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(1L, 2L);
        hashMap.put(2L, 4L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        productsLocations.add(new ProductsLocations(1L, 7L, 1L));
        productsLocations.add(new ProductsLocations(2L, 7L, 2L));
        productsLocations.add(new ProductsLocations(1L, 8L, 1L));
        productsLocations.add(new ProductsLocations(2L, 8L, 2L));
        addAddress(productsLocations);

        Assert.assertEquals(productsLocations, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test
    public void twoLocationsNeedsFromBoth() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(1L, 1L);
        hashMap.put(2L, 3L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        List<ProductsLocations> expected = new ArrayList<>();
        productsLocations.add(new ProductsLocations(1L, 7L, 1L));
        productsLocations.add(new ProductsLocations(2L, 7L, 2L));
        productsLocations.add(new ProductsLocations(1L, 8L, 1L));
        productsLocations.add(new ProductsLocations(2L, 8L, 2L));
        addAddress(productsLocations);
        expected.addAll(productsLocations.subList(0, 2));
        expected.add(new ProductsLocations(2L, 8L, 1L));

        Assert.assertEquals(expected, quantityStrategy.getLocations(hashMap, productsLocations));
    }
}
