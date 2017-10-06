package ro.msg.learning.shop.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.entities.ProductCategory;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@RunWith(SpringRunner.class)
@JdbcTest
@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ContextConfiguration(classes = ShopApplication.class)
public class CategoryJDBCTest {

    @Autowired
    private CategoryJDBC categoryJDBC;

    @Test
    public void findAllUsers() {
        List<ProductCategory> productCategories = categoryJDBC.findAll();
        assertNotNull(productCategories);
        assertFalse(productCategories.isEmpty());
    }

    @Test
    public void findById() {
        ProductCategory productCategory = categoryJDBC.findById(1);
        assertNotNull(productCategory);
    }


    @Test
    public void testCreate() throws Exception {
        ProductCategory productCategory = new ProductCategory(100,"Test");
        ProductCategory productCategory2 = categoryJDBC.create(productCategory);
        ProductCategory productCategory3 = categoryJDBC.findById(productCategory2.getId());
        assertNotNull(productCategory3);
        assertEquals("Test", productCategory3.getName());
    }
}
