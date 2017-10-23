package ro.msg.learning.shop.services.strategy;

import ro.msg.learning.shop.entities.ProductsLocations;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 * Most “abundant” locations: for each required product, select the location that has the largest stock for that given product.
 */
public class MostAbundantQS implements QuantityStrategy {

    @Override
    public List<ProductsLocations> getLocations(Map<Long, Long> productList, List<ProductsLocations> locationAndProduct) {
        locationAndProduct.sort(Comparator.comparingLong(ProductsLocations::getQuantity).reversed());
        return new GreedyQS().getLocations(productList, locationAndProduct);
    }
}
