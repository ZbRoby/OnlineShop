package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.models.ShelfProduct;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser(username = "customer", authorities = "CUSTOMER")
public class ProductPresenterIntegrationTest {

    @Autowired
    private ProductPresenter productPresenter;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getProductListTest() {
        List<Product> products = productRepository.findAll();
        List<ShelfProduct> actual = productPresenter.getProductList();
        Assert.assertEquals(products.size(), actual.size());
    }
}
