package ro.msg.learning.shop.services.strategies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.models.LocationDistance;
import ro.msg.learning.shop.models.gdistance.Element;
import ro.msg.learning.shop.models.gdistance.TimeAndSpace;
import ro.msg.learning.shop.models.gdistance.enums.ElementGoogleStatus;
import ro.msg.learning.shop.repositories.CustomerRepository;
import ro.msg.learning.shop.utils.DistanceCalculator;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

public class ProximityQSTest {

    @Mock
    private Authentication authentication;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private DistanceCalculator distanceCalculator;

    private QuantityStrategy quantityStrategy;


    private void mockCustomerRepository() {
        Mockito.when(customerRepository.findOptionalByUserUsername(Mockito.anyString())).thenReturn(Optional.of(getCustomer()));
    }

    private void mockDistanceCalculator() {
        Mockito.when(distanceCalculator.sortLocations(Mockito.any(Address.class),
            Mockito.anyListOf(ProductsLocations.class))).thenAnswer(
            answer -> {
                List<ProductsLocations> list = answer.getArgumentAt(1, List.class);
                List<LocationDistance> returnList = new ArrayList<>();
                for (ProductsLocations productsLocation : list) {
                    if (productsLocation.getLocationId() == 7L) {//{distance:3961323}
                        returnList.add(getLocationDistance(productsLocation, 3961323));
                    }
                    if (productsLocation.getLocationId() == 8L) {//{distance:4119896}
                        returnList.add(getLocationDistance(productsLocation, 4119896));
                    }
                }
                returnList.sort(Comparator.comparingDouble(o -> o.getDistance().getDistance().getValue()));
                return returnList.stream().map(LocationDistance::getLocation).collect(Collectors.toList());

            }
        );
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockCustomerRepository();
        mockDistanceCalculator();
        quantityStrategy = new ProximityQS(customerRepository, distanceCalculator);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private LocationDistance getLocationDistance(ProductsLocations productsLocations, double distance) {
        LocationDistance locationDistance = new LocationDistance();
        locationDistance.setLocation(productsLocations);
        TimeAndSpace distanceTimeAndSpace = new TimeAndSpace(null, distance);
        locationDistance.setDistance(new Element(distanceTimeAndSpace, null, ElementGoogleStatus.OK));
        return locationDistance;
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
                productsLocation.setLocation(getLocation("Cibebek", "Indonesia"));//{distance: 3961323}
            }
            if (productsLocation.getLocationId() == 8L) {
                productsLocation.setLocation(getLocation("Rancabuaya", "Indonesia"));//{distance:4119896}
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
