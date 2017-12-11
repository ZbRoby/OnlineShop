package ro.msg.learning.shop.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.models.ShelfProduct;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class ProductPresenterTest {

    @InjectMocks
    private ProductPresenter productPresenter;

    @Mock
    private ProductRepository mockProductRepository;

    private List<ProductsLocations> productsLocationsList;

    private void setUpProductsLocationsList() {
        productsLocationsList = new ArrayList<>();
        productsLocationsList.add(new ProductsLocations(10L, 11L, 1L));
        productsLocationsList.add(new ProductsLocations(17L, 10L, 1L));
        productsLocationsList.add(new ProductsLocations(12L, 11L, 3L));
        productsLocationsList.add(new ProductsLocations(20L, 13L, 1L));
        productsLocationsList.add(new ProductsLocations(10L, 13L, 2L));
        productsLocationsList.add(new ProductsLocations(15L, 14L, 2L));
        productsLocationsList.add(new ProductsLocations(18L, 17L, 6L));
        productsLocationsList.add(new ProductsLocations(11L, 17L, 1L));
        productsLocationsList.add(new ProductsLocations(14L, 18L, 2L));
        productsLocationsList.add(new ProductsLocations(13L, 18L, 1L));
        productsLocationsList.add(new ProductsLocations(16L, 20L, 2L));
        productsLocationsList.add(new ProductsLocations(16L, 20L, 1L));
        productsLocationsList.add(new ProductsLocations(12L, 20L, 2L));
    }

    private void setUpMockProductRepository() {
        when(mockProductRepository.findAll()).thenAnswer(
            (Answer<List<Product>>) invocation -> {
                List<Product> products = new ArrayList<>();
                for (ProductsLocations productsLocations : productsLocationsList) {
                    Product p = new Product();
                    p.setId(productsLocations.getProductId());
                    products.add(p);
                }
                return products;
            }
        );
        when(mockProductRepository.getQuantityForProduct(anyLong())).thenAnswer(
            (Answer<Long>) invocation -> productsLocationsList.stream().
                filter(x -> x.getProductId().equals(invocation.getArguments()[0])).
                mapToLong(ProductsLocations::getQuantity).sum()
        );
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpProductsLocationsList();
        setUpMockProductRepository();
    }

    @Test
    public void getProduct() {
        List<ShelfProduct> actual = productPresenter.getProductList();
        Assert.assertEquals(productsLocationsList.size(), actual.size());
    }
}

