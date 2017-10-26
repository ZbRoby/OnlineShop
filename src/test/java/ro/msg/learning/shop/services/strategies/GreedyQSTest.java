package ro.msg.learning.shop.services.strategies;

import org.junit.Assert;
import org.junit.Test;
import ro.msg.learning.shop.entities.ProductsLocations;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class GreedyQSTest {

    private QuantityStrategy quantityStrategy = new GreedyQS();

    @Test
    public void emptyProductMap() {
        Assert.assertTrue(quantityStrategy.getLocations(new HashMap<>(), new ArrayList<>()).isEmpty());
    }

    @Test(expected = InvalidParameterException.class)
    public void notEnoughQuantity() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(1L, 1L);
        hashMap.put(2L, 3L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        quantityStrategy.getLocations(hashMap, productsLocations);
    }

    @Test
    public void oneLocation() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(1L, 1L);
        hashMap.put(2L, 2L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        productsLocations.add(new ProductsLocations(1L, 7L, 1L));
        productsLocations.add(new ProductsLocations(2L, 7L, 2L));

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
        expected.addAll(productsLocations.subList(0, 2));
        expected.add(new ProductsLocations(2L, 8L, 1L));

        Assert.assertEquals(expected, quantityStrategy.getLocations(hashMap, productsLocations));
    }

}
