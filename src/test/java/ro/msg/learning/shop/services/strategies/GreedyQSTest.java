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
        hashMap.put(3L, 3L);
        quantityStrategy.getLocations(hashMap, new ArrayList<>());
    }

    @Test
    public void oneLocation() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(3L, 3L);
        hashMap.put(4L, 4L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        productsLocations.add(new ProductsLocations(3L, 6L, 3L));
        productsLocations.add(new ProductsLocations(4L, 6L, 4L));

        Assert.assertEquals(productsLocations, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test
    public void twoLocationsEnoughInFirst() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(3L, 3L);
        hashMap.put(4L, 4L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        List<ProductsLocations> expected = new ArrayList<>();
        productsLocations.add(new ProductsLocations(3L, 6L, 3L));
        productsLocations.add(new ProductsLocations(4L, 6L, 4L));
        productsLocations.add(new ProductsLocations(3L, 9L, 3L));
        productsLocations.add(new ProductsLocations(4L, 9L, 4L));
        expected.addAll(productsLocations.subList(0, 2));

        Assert.assertEquals(expected, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test
    public void twoLocationsNeedsBoth() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(3L, 6L);
        hashMap.put(4L, 8L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        productsLocations.add(new ProductsLocations(3L, 6L, 3L));
        productsLocations.add(new ProductsLocations(4L, 6L, 4L));
        productsLocations.add(new ProductsLocations(3L, 9L, 3L));
        productsLocations.add(new ProductsLocations(4L, 9L, 4L));

        Assert.assertEquals(productsLocations, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test
    public void twoLocationsNeedsFromBoth() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(3L, 3L);
        hashMap.put(4L, 6L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        List<ProductsLocations> expected = new ArrayList<>();
        productsLocations.add(new ProductsLocations(3L, 6L, 3L));
        productsLocations.add(new ProductsLocations(4L, 6L, 4L));
        productsLocations.add(new ProductsLocations(3L, 9L, 3L));
        productsLocations.add(new ProductsLocations(4L, 9L, 4L));
        expected.addAll(productsLocations.subList(0, 2));
        expected.add(new ProductsLocations(4L, 9L, 2L));

        Assert.assertEquals(expected, quantityStrategy.getLocations(hashMap, productsLocations));
    }

}
