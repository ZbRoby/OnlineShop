package ro.msg.learning.shop.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.local.util.TokenGetter;
import ro.msg.learning.shop.models.ShelfProduct;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductPresenterControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String token;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        TokenGetter tokenGetter = new TokenGetter(testRestTemplate);
        token = tokenGetter.getCustomerToken();
    }

    @Test
    public void seeProductsTest() {
        ResponseEntity<ShelfProduct[]> forEntity = testRestTemplate.getForEntity("/rest/seeProducts?access_token=" + token, ShelfProduct[].class);
        Assert.assertNotNull(forEntity.getBody());
        if (forEntity.getBody().length >= 1) {
            Assert.assertNotEquals(0, forEntity.getBody()[0].getProduct().getId());
        }
        Assert.assertTrue(forEntity.getStatusCode().is2xxSuccessful());
    }
}
