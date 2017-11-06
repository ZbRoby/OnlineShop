package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class StockExporterTest {

    @InjectMocks
    private StockExporter stockExporter;

    @Mock
    private ProductRepository mockProductRepository;

    private List<ProductsLocations> productsLocationsList;

    private void setUpProductsLocationsList() {
        productsLocationsList = new ArrayList<>();
        productsLocationsList.add(new ProductsLocations(10L, 10L, 105L));
        productsLocationsList.add(new ProductsLocations(17L, 10L, 105L));
        productsLocationsList.add(new ProductsLocations(12L, 11L, 103L));
        productsLocationsList.add(new ProductsLocations(20L, 13L, 100L));
        productsLocationsList.add(new ProductsLocations(10L, 13L, 205L));
        productsLocationsList.add(new ProductsLocations(15L, 14L, 201L));
        productsLocationsList.add(new ProductsLocations(18L, 17L, 207L));
        productsLocationsList.add(new ProductsLocations(11L, 17L, 109L));
        productsLocationsList.add(new ProductsLocations(14L, 18L, 207L));
        productsLocationsList.add(new ProductsLocations(13L, 18L, 103L));
        productsLocationsList.add(new ProductsLocations(16L, 20L, 204L));
        productsLocationsList.add(new ProductsLocations(16L, 20L, 109L));
        productsLocationsList.add(new ProductsLocations(12L, 20L, 204L));
    }

    private void setUpMockProductRepository() {
        when(mockProductRepository.findAllProductsLocationsInSet(Mockito.anySetOf(Long.class))).
            thenAnswer(
                (Answer<List<ProductsLocations>>) invocation ->
                    productsLocationsList.stream().filter(x -> ((Set) invocation.getArguments()[0]).
                        contains(x.getProductId())).collect(Collectors.toList())
            );
        when(mockProductRepository.findAllProductsLocationsWithLocationId(anyLong())).
            thenAnswer(
                (Answer<List<ProductsLocations>>) invocation -> {
                    List<ProductsLocations> collect = productsLocationsList.stream().
                        filter(x -> x.getLocationId().equals(invocation.getArguments()[0])).
                        collect(Collectors.toList());
                    return collect;
                }
            );
        when(mockProductRepository.findAllProductsLocations()).thenReturn(productsLocationsList);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpProductsLocationsList();
        setUpMockProductRepository();
    }

    @Test
    public void existentLocationGetStockOfLocationTest() {
        long locationId = 10L;
        List<ProductsLocations> expected = productsLocationsList.stream().
            filter(x -> x.getLocationId() == locationId).collect(Collectors.toList());
        Assert.assertEquals(expected, stockExporter.getStockOfLocation(locationId));
    }

    @Test
    public void inexistentLocationGetStockOfLocationTest() {
        long locationId = 21L;
        List<ProductsLocations> expected = productsLocationsList.stream().
            filter(x -> x.getLocationId() == locationId).collect(Collectors.toList());
        Assert.assertEquals(expected, stockExporter.getStockOfLocation(locationId));
    }

    @Test
    public void getStockTest() {
        Assert.assertEquals(productsLocationsList, stockExporter.getStock());
    }
}
