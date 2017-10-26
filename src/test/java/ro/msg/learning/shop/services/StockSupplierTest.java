package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.when;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class StockSupplierTest {

    @InjectMocks
    StockSupplier stockSupplier;

    @Mock
    ProductRepository mockProductRepository;

    List<ProductsLocations> productsLocationsList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productsLocationsList = new ArrayList<>();
        productsLocationsList.add(new ProductsLocations(10L, 10L, 15L));
        productsLocationsList.add(new ProductsLocations(17L, 10L, 15L));
        productsLocationsList.add(new ProductsLocations(12L, 11L, 13L));
        productsLocationsList.add(new ProductsLocations(20L, 13L, 10L));
        productsLocationsList.add(new ProductsLocations(10L, 13L, 25L));
        productsLocationsList.add(new ProductsLocations(15L, 14L, 21L));
        productsLocationsList.add(new ProductsLocations(18L, 17L, 27L));
        productsLocationsList.add(new ProductsLocations(11L, 17L, 19L));
        productsLocationsList.add(new ProductsLocations(14L, 18L, 27L));
        productsLocationsList.add(new ProductsLocations(13L, 18L, 13L));
        productsLocationsList.add(new ProductsLocations(16L, 20L, 24L));
        productsLocationsList.add(new ProductsLocations(16L, 20L, 19L));
        productsLocationsList.add(new ProductsLocations(12L, 20L, 24L));
        when(mockProductRepository.findAllProductsLocationsInSet(anySet())).
            thenAnswer(
                (Answer<List<ProductsLocations>>) invocation ->
                    productsLocationsList.stream().filter(x -> ((Set) invocation.getArguments()[0]).
                        contains(x.getProductId())).collect(Collectors.toList())
            );
    }

    @Test
    public void emptyProductMap() {
        stockSupplier.addStock(new HashMap<Long, Long>());
    }

    @Test
    public void correctProductMapWithNoDuplicate() {
        HashMap<Long, Long> productMap = new HashMap<Long, Long>();
        productMap.put(14L, 29L);
        productMap.put(20L, 19L);
        productMap.put(11L, 21L);
        List<ProductsLocations> actual = productsLocationsList;
        actual.get(3).setQuantity(actual.get(3).getQuantity() + 19);
        actual.get(7).setQuantity(actual.get(7).getQuantity() + 21);
        actual.get(8).setQuantity(actual.get(8).getQuantity() + 29);
        stockSupplier.addStock(productMap);
        Assert.assertEquals(actual, productsLocationsList);
    }

    @Test
    public void correctProductMapWithDuplicate() {
        HashMap<Long, Long> productMap = new HashMap<Long, Long>();
        productMap.put(16L, 29L);
        productMap.put(20L, 19L);
        productMap.put(11L, 21L);
        List<ProductsLocations> actual = productsLocationsList;
        actual.get(3).setQuantity(actual.get(3).getQuantity() + 19);
        actual.get(7).setQuantity(actual.get(7).getQuantity() + 21);
        actual.get(12).setQuantity(actual.get(12).getQuantity() + 29);
        stockSupplier.addStock(productMap);
        Assert.assertEquals(actual, productsLocationsList);
    }

    @Test(expected = ProductNotFoundException.class)
    public void incorrectProductMap() {
        HashMap<Long, Long> productMap = new HashMap<Long, Long>();
        productMap.put(14L, 29L);
        productMap.put(20L, 19L);
        productMap.put(11L, 21L);
        productMap.put(30L, 21L);
        stockSupplier.addStock(productMap);
    }
}
