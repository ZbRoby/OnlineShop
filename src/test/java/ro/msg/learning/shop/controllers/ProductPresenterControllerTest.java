package ro.msg.learning.shop.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.models.ShelfProduct;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductPresenterControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void seeProductsTest() {
        ResponseEntity<ShelfProduct[]> forEntity = testRestTemplate.getForEntity("/rest/seeProducts", ShelfProduct[].class);
        Assert.assertNotNull(forEntity.getBody());
        if (forEntity.getBody().length >= 1) {
            Assert.assertNotEquals(0, forEntity.getBody()[0].getProduct().getId());
        }
        Assert.assertTrue(forEntity.getStatusCode().is2xxSuccessful());
    }
}
