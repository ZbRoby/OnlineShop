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

    private String getToken() {
        try {
            String token = testRestTemplate.withBasicAuth("android", "123456")
                .postForObject("/oauth/token?" +
                    "grant_type=" + "password" +
                    "&username=" + "admin" +
                    "&password=" + "test" +
                    "", null, String.class);
            return token.split("\"")[3];
        } catch (Exception e) {
            e.printStackTrace();
            return "StockExporterControllerTestError";
        }
    }

    @Test
    public void getStockTest() {
        ResponseEntity<PLQList> forEntity = testRestTemplate.getForEntity("/rest/stock?access_token=" + getToken(), PLQList.class);
        Assert.assertTrue(forEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getStockParameterTest() {
        long id = 1;
        ResponseEntity<PLQList> forEntity = testRestTemplate.getForEntity("/rest/stock/" + id + "?access_token=" + getToken(), PLQList.class);
        Assert.assertTrue(forEntity.getStatusCode().is2xxSuccessful());
    }
}
