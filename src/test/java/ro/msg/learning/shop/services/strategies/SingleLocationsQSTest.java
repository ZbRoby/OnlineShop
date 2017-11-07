package ro.msg.learning.shop.services.strategies;

import org.junit.Assert;
import org.junit.Test;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.exceptions.NoSingleLocationFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class SingleLocationsQSTest {

    private QuantityStrategy quantityStrategy = new SingleLocationQS();

    @Test
    public void emptyProductMap() {
        Assert.assertTrue(quantityStrategy.getLocations(new HashMap<>(), new ArrayList<>()).isEmpty());
    }

    @Test(expected = NoSingleLocationFoundException.class)
    public void notEnoughQuantity() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(3L, 3L);
        quantityStrategy.getLocations(hashMap, new ArrayList<>());
    }

    @Test
    public void oneLocation() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(5L, 1L);
        hashMap.put(6L, 2L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        productsLocations.add(new ProductsLocations(5L, 7L, 1L));
        productsLocations.add(new ProductsLocations(6L, 7L, 2L));

        Assert.assertEquals(productsLocations, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test
    public void twoLocationsEnoughInFirst() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(5L, 1L);
        hashMap.put(6L, 2L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        List<ProductsLocations> expected = new ArrayList<>();
        productsLocations.add(new ProductsLocations(5L, 7L, 1L));
        productsLocations.add(new ProductsLocations(6L, 7L, 2L));
        productsLocations.add(new ProductsLocations(5L, 8L, 1L));
        productsLocations.add(new ProductsLocations(6L, 8L, 2L));
        expected.add(productsLocations.get(0));
        expected.add(productsLocations.get(1));

        Assert.assertEquals(expected, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test(expected = NoSingleLocationFoundException.class)
    public void twoLocationsNeedsBoth() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(5L, 2L);
        hashMap.put(6L, 4L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        productsLocations.add(new ProductsLocations(5L, 7L, 1L));
        productsLocations.add(new ProductsLocations(6L, 7L, 2L));
        productsLocations.add(new ProductsLocations(5L, 8L, 1L));
        productsLocations.add(new ProductsLocations(6L, 8L, 2L));
        quantityStrategy.getLocations(hashMap, productsLocations);
    }

    @Test(expected = NoSingleLocationFoundException.class)
    public void twoLocationsNeedsFromBoth() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(5L, 1L);
        hashMap.put(6L, 3L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        productsLocations.add(new ProductsLocations(5L, 7L, 1L));
        productsLocations.add(new ProductsLocations(6L, 7L, 2L));
        productsLocations.add(new ProductsLocations(5L, 8L, 1L));
        productsLocations.add(new ProductsLocations(6L, 8L, 2L));
        quantityStrategy.getLocations(hashMap, productsLocations);
    }
}
