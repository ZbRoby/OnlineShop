package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.repositories.ProductRepository;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser(username = "admin",authorities = "ADMIN")
public class StockExporterIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockExporter stockExporter;

    @Test
    public void existentLocationGetStockOfLocationTest() {
        long locationId = 10L;
        Assert.assertEquals(productRepository.findAllProductsLocationsWithLocationId(locationId),
            stockExporter.getStockOfLocation(locationId));
    }

    @Test
    public void inexistentLocationGetStockOfLocationTest() {
        long locationId = 21L;
        Assert.assertEquals(productRepository.findAllProductsLocationsWithLocationId(locationId),
            stockExporter.getStockOfLocation(locationId));
    }

    @Test
    public void getStockTest() {
        Assert.assertEquals(productRepository.findAllProductsLocations(), stockExporter.getStock());
    }
}
