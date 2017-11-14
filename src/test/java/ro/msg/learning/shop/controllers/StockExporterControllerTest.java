package ro.msg.learning.shop.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.models.PLQList;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockExporterControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getStockTest() {
        ResponseEntity<PLQList> forEntity = testRestTemplate.getForEntity("/rest/stock", PLQList.class);
        Assert.assertNotNull(forEntity.getBody());
        if (forEntity.getBody().getList().size() >= 1) {
            Assert.assertNotEquals(0, forEntity.getBody().getList().get(0).getProductId());
        }
        Assert.assertTrue(forEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getStockParameterTest() {
        long id = 1;
        ResponseEntity<PLQList> forEntity = testRestTemplate.getForEntity("/rest/stock/" + id, PLQList.class);
        Assert.assertNotNull(forEntity.getBody());
        if (forEntity.getBody().getList().size() >= 1) {
            Assert.assertNotEquals(0, forEntity.getBody().getList().get(0).getProductId());
        }
        Assert.assertTrue(forEntity.getStatusCode().is2xxSuccessful());
    }
}
