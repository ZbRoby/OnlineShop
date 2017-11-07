package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.HashMap;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StockSupplierIntegrationTest {

    @Autowired
    private StockSupplier stockSupplier;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void emptyProductMapTest() {
        List<ProductsLocations> actual = productRepository.findAllProductsLocations();
        stockSupplier.addStock(new HashMap<>());
        Assert.assertEquals(actual, productRepository.findAllProductsLocations());
    }

    @Test
    public void correctProductMapWithNoDuplicateTest() {
        HashMap<Long, Long> productMap = new HashMap<>();
        productMap.put(10L, 29L);
        productMap.put(13L, 19L);
        productMap.put(15L, 21L);
        List<ProductsLocations> actual = productRepository.findAllProductsLocations();
        stockSupplier.addStock(productMap);

        Assert.assertEquals("Testing for productId 10",
            actual.stream().filter(x -> x.getProductId().equals(10L)).
                mapToLong(ProductsLocations::getQuantity).sum() + 29L,
            productRepository.findAllProductsLocations().stream().filter(x -> x.getProductId().equals(10L)).
                mapToLong(ProductsLocations::getQuantity).sum());

        Assert.assertEquals("Testing for productId 13",
            actual.stream().filter(x -> x.getProductId().equals(13L)).
                mapToLong(ProductsLocations::getQuantity).sum() + 19L,
            productRepository.findAllProductsLocations().stream().filter(x -> x.getProductId().equals(13L)).
                mapToLong(ProductsLocations::getQuantity).sum());

        Assert.assertEquals("Testing for productId 15",
            actual.stream().filter(x -> x.getProductId().equals(15L)).
                mapToLong(ProductsLocations::getQuantity).sum() + 21L,
            productRepository.findAllProductsLocations().stream().filter(x -> x.getProductId().equals(15L)).
                mapToLong(ProductsLocations::getQuantity).sum());
    }

    @Test
    public void correctProductMapWithDuplicateTest() {
        HashMap<Long, Long> productMap = new HashMap<>();
        productMap.put(10L, 29L);
        productMap.put(13L, 19L);
        productMap.put(1L, 21L);
        List<ProductsLocations> actual = productRepository.findAllProductsLocations();
        stockSupplier.addStock(productMap);

        Assert.assertEquals("Testing for productId 10",
            actual.stream().filter(x -> x.getProductId().equals(10L)).
                mapToLong(ProductsLocations::getQuantity).sum() + 29L,
            productRepository.findAllProductsLocations().stream().filter(x -> x.getProductId().equals(10L)).
                mapToLong(ProductsLocations::getQuantity).sum());

        Assert.assertEquals("Testing for productId 13",
            actual.stream().filter(x -> x.getProductId().equals(13L)).
                mapToLong(ProductsLocations::getQuantity).sum() + 19L,
            productRepository.findAllProductsLocations().stream().filter(x -> x.getProductId().equals(13L)).
                mapToLong(ProductsLocations::getQuantity).sum());

        Assert.assertEquals("Testing for productId 1",
            actual.stream().filter(x -> x.getProductId().equals(1L)).
                mapToLong(ProductsLocations::getQuantity).sum() + 21L,
            productRepository.findAllProductsLocations().stream().filter(x -> x.getProductId().equals(1L)).
                mapToLong(ProductsLocations::getQuantity).sum());
    }

    @Test(expected = ProductNotFoundException.class)
    public void incorrectProductMapTest() {
        HashMap<Long, Long> productMap = new HashMap<>();
        productMap.put(14L, 20L);
        productMap.put(20L, 10L);
        productMap.put(11L, 20L);
        productMap.put(30L, 20L);
        stockSupplier.addStock(productMap);
    }
}
