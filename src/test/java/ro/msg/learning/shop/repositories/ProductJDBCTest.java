package ro.msg.learning.shop.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.entities.Product;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RunWith(SpringRunner.class)
@JdbcTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ContextConfiguration(classes = ShopApplication.class)
public class ProductJDBCTest {

    @Autowired
    private ProductJDBC productJDBC;

    @Test
    public void findAllUsers() {
        List<Product> products = productJDBC.findAll();
        assertNotNull(products);
        assertTrue(!products.isEmpty());
    }

    @Test
    public void findUserById() {
        Product product = productJDBC.findById(1);
        assertNotNull(product);
    }


    @Test
    public void testCreate() throws Exception {
        Product product = new Product();
        product.setId(0);
        product.setName("Test");
        Product product2 = productJDBC.create(product);
        Product product3 = productJDBC.findById(product2.getId());
        assertNotNull(product3);
        assertEquals("Test", product3.getName());
    }
}
