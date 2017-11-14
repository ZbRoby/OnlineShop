package ro.msg.learning.shop.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.models.PLQList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockSupplierControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void resupplyTest() {
        List<ProductsLocations> productsLocationsList = new ArrayList<>();
        productsLocationsList.add(new ProductsLocations(1L, 4L, 3));
        productsLocationsList.add(new ProductsLocations(2L, 8L, 3));
        productsLocationsList.add(new ProductsLocations(9L, 7L, 11));
        PLQList plqList = new PLQList(productsLocationsList);
        ResponseEntity<Object> voidResponseEntity = testRestTemplate.postForEntity("/rest/resupply", plqList, Object.class);
        Assert.assertTrue(voidResponseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void resupplyTestNoProduct() {
        List<ProductsLocations> productsLocationsList = new ArrayList<>();
        productsLocationsList.add(new ProductsLocations(100L, 4L, 3));
        PLQList plqList = new PLQList(productsLocationsList);
        ResponseEntity<String> voidResponseEntity = testRestTemplate.postForEntity("/rest/resupply", plqList, String.class);
        Assert.assertTrue(voidResponseEntity.getStatusCode().is4xxClientError());
    }

}
