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
public class MostAbundantQSTest {

    private QuantityStrategy quantityStrategy = new MostAbundantQS();

    @Test
    public void emptyProductMap() {
        Assert.assertTrue(quantityStrategy.getLocations(new HashMap<>(), new ArrayList<>()).isEmpty());
    }

    @Test(expected = InvalidParameterException.class)
    public void notEnoughQuantity() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(2L, 2L);
        quantityStrategy.getLocations(hashMap, new ArrayList<>());
    }

    @Test
    public void oneLocation() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(3L, 1L);
        hashMap.put(4L, 2L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        productsLocations.add(new ProductsLocations(3L, 7L, 1L));
        productsLocations.add(new ProductsLocations(4L, 7L, 2L));

        Assert.assertEquals(productsLocations, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test
    public void twoLocationsEnoughInFirst() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(3L, 1L);
        hashMap.put(4L, 2L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        List<ProductsLocations> expected = new ArrayList<>();
        productsLocations.add(new ProductsLocations(3L, 7L, 1L));
        productsLocations.add(new ProductsLocations(4L, 7L, 2L));
        productsLocations.add(new ProductsLocations(3L, 8L, 1L));
        productsLocations.add(new ProductsLocations(4L, 8L, 2L));
        expected.add(productsLocations.get(1));
        expected.add(productsLocations.get(0));

        Assert.assertEquals(expected, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test
    public void twoLocationsNeedsBoth() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(3L, 2L);
        hashMap.put(4L, 4L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        productsLocations.add(new ProductsLocations(3L, 7L, 1L));
        productsLocations.add(new ProductsLocations(4L, 7L, 2L));
        productsLocations.add(new ProductsLocations(3L, 8L, 1L));
        productsLocations.add(new ProductsLocations(4L, 8L, 2L));

        Assert.assertEquals(productsLocations, quantityStrategy.getLocations(hashMap, productsLocations));
    }

    @Test
    public void twoLocationsNeedsFromBoth() {
        HashMap<Long, Long> hashMap = new HashMap<>();
        hashMap.put(3L, 1L);
        hashMap.put(4L, 3L);
        List<ProductsLocations> productsLocations = new ArrayList<>();
        List<ProductsLocations> expected = new ArrayList<>();
        productsLocations.add(new ProductsLocations(3L, 7L, 1L));
        productsLocations.add(new ProductsLocations(4L, 7L, 2L));
        productsLocations.add(new ProductsLocations(3L, 8L, 1L));
        productsLocations.add(new ProductsLocations(4L, 8L, 2L));
        expected.add(new ProductsLocations(4L, 7L, 2L));
        expected.add(new ProductsLocations(4L, 8L, 1L));
        expected.add(new ProductsLocations(3L, 7L, 1L));

        Assert.assertEquals(expected, quantityStrategy.getLocations(hashMap, productsLocations));
    }
}
