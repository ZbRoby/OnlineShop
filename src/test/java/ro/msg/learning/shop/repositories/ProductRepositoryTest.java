package ro.msg.learning.shop.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductsLocations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    @Test
    public void findByNameTest() {
        String name = "Namfix";
        Product temp = repository.findByName(name);
        Assert.assertNotNull(temp);
        Assert.assertTrue(temp.getName().equals(name));
    }

    @Test
    public void findAllByPriceGreaterThanEqualTest() {
        double priceAvg = 400;
        double priceMin = 0;
        double priceMax = Double.MAX_VALUE;

        assertTrue("Test PriceGreaterThanEqual:[" + priceAvg + "] True", this.repository.findAllByPriceGreaterThanEqual(priceAvg).stream().allMatch(product -> product.getPrice() >= priceAvg));
        assertTrue("Test PriceGreaterThanEqual:[" + priceMin + "] True", this.repository.findAllByPriceGreaterThanEqual(priceMin).stream().allMatch(product -> product.getPrice() >= priceMin));
        assertTrue("Test PriceGreaterThanEqual:[" + priceMax + "] True", this.repository.findAllByPriceGreaterThanEqual(priceMax).stream().allMatch(product -> product.getPrice() >= priceMax));

        assertFalse("Test PriceGreaterThanEqual:[" + priceAvg + "] True", this.repository.findAllByPriceGreaterThanEqual(priceAvg).stream().anyMatch(product -> product.getPrice() < priceAvg));
        assertFalse("Test PriceGreaterThanEqual:[" + priceMin + "] True", this.repository.findAllByPriceGreaterThanEqual(priceMin).stream().anyMatch(product -> product.getPrice() < priceMin));
        assertFalse("Test PriceGreaterThanEqual:[" + priceMax + "] True", this.repository.findAllByPriceGreaterThanEqual(priceMax).stream().anyMatch(product -> product.getPrice() < priceMax));
    }

    @Test
    public void findAllByPriceLessThanEqualTest() {
        double priceAvg = 400;
        double priceMin = 0;
        double priceMax = Double.MAX_VALUE;

        assertTrue("Test PriceLessThanEqual:[" + priceAvg + "] True", this.repository.findAllByPriceLessThanEqual(priceAvg).stream().allMatch(product -> product.getPrice() <= priceAvg));
        assertTrue("Test PriceLessThanEqual:[" + priceMin + "] True", this.repository.findAllByPriceLessThanEqual(priceMin).stream().allMatch(product -> product.getPrice() <= priceMin));
        assertTrue("Test PriceLessThanEqual:[" + priceMax + "] True", this.repository.findAllByPriceLessThanEqual(priceMax).stream().allMatch(product -> product.getPrice() <= priceMax));

        assertFalse("Test PriceLessThanEqual:[" + priceAvg + "] True", this.repository.findAllByPriceLessThanEqual(priceAvg).stream().anyMatch(product -> product.getPrice() > priceAvg));
        assertFalse("Test PriceLessThanEqual:[" + priceMin + "] True", this.repository.findAllByPriceLessThanEqual(priceMin).stream().anyMatch(product -> product.getPrice() > priceMin));
        assertFalse("Test PriceLessThanEqual:[" + priceMax + "] True", this.repository.findAllByPriceLessThanEqual(priceMax).stream().anyMatch(product -> product.getPrice() > priceMax));
    }

    @Test
    public void getQuantityForProductTest() {
        repository.findAll().forEach(x -> Assert.assertTrue("Test " + x.getId(), x.getProductsLocations().stream().mapToLong(ProductsLocations::getQuantity).sum() == repository.getQuantityForProduct(x.getId())));
    }
}
