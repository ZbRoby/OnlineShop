package ro.msg.learning.shop.services.strategies;

import ro.msg.learning.shop.entities.ProductsLocations;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 * Greedy: take from each location as many products as possible.
 */

public class GreedyQS implements QuantityStrategy {

    @Override
    public List<ProductsLocations> getLocations(Map<Long, Long> productList, List<ProductsLocations> locationAndProduct) {
        List<ProductsLocations> usedGroup = new ArrayList<>();
        for (ProductsLocations productsLocations : locationAndProduct) {
            Long temp = productList.get(productsLocations.getProductId());
            if (temp != null) {
                if (productsLocations.getQuantity() - temp >= 0) {
                    usedGroup.add(new ProductsLocations(productsLocations.getProductId(), productsLocations.getLocationId(), temp));
                    productList.remove(productsLocations.getProductId());
                } else {
                    usedGroup.add(new ProductsLocations(productsLocations.getProductId(), productsLocations.getLocationId(), productsLocations.getQuantity()));
                    productList.put(productsLocations.getProductId(), temp - productsLocations.getQuantity());
                }
            }
        }

        if (productList.isEmpty()) {
            return usedGroup;
        } else {
            throw new InvalidParameterException("productList= could not get the quantity for all products from the locationAndProduct=");
        }
    }
}
