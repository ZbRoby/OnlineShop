package ro.msg.learning.shop.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.local.util.TokenGetter;
import ro.msg.learning.shop.models.PLQList;
import ro.msg.learning.shop.repositories.ProductRepository;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockExporterControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ProductRepository productRepository;

    private String token;

    @Before
    public void setUp() throws Exception {
        TokenGetter tokenGetter = new TokenGetter(testRestTemplate);
        token = tokenGetter.getAdminToken();
    }

    @Test
    public void getStockTest() {
        ResponseEntity<PLQList> forEntity = testRestTemplate.getForEntity("/rest/stock?access_token=" + token, PLQList.class);
        Assert.assertTrue(forEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getStockParameterTest() {
        if (productRepository.count() > 0) {
            long id = 1;
            ResponseEntity<PLQList> forEntity = testRestTemplate.getForEntity("/rest/stock/" + id + "?access_token=" + token, PLQList.class);
            Assert.assertTrue(forEntity.getStatusCode().is2xxSuccessful());
        }
    }
}
