package ro.msg.learning.shop.services.strategy;

import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.exceptions.NoSingleLocationFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 * Single location: find a single location that has all the required products (with the required quantities) in stock.
 */
public class SingleLocationQS implements QuantityStrategy {

    private long getLocationId(long numberProducts, List<ProductsLocations> usedGroup) {
        long tempLocationId = -1;
        long tempNumberProd = numberProducts;

        if (!usedGroup.isEmpty()) {
            tempLocationId = usedGroup.get(0).getLocationId();
            for (ProductsLocations productsLocations : usedGroup) {
                if (productsLocations.getLocationId().equals(tempLocationId)) {
                    tempNumberProd--;
                } else {
                    tempLocationId = productsLocations.getLocationId();
                    tempNumberProd = numberProducts;
                    tempNumberProd--;
                }
                if (tempNumberProd == 0) {
                    break;
                }
            }
        }

        if (tempNumberProd == 0) {
            return tempLocationId;
        } else {
            return -1;
        }
    }

    @Override
    public List<ProductsLocations> getLocations(Map<Long, Long> productList, List<ProductsLocations> locationAndProduct) {
        List<ProductsLocations> usedGroup = new ArrayList<>();
        for (ProductsLocations productsLocations : locationAndProduct) {
            if (productsLocations.getQuantity() >= productList.get(productsLocations.getProductId())) {
                usedGroup.add(new ProductsLocations(productsLocations.getProductId(), productsLocations.getLocationId(), productsLocations.getQuantity()));
            }
        }
        final long finalTempLocationId = getLocationId(productList.size(), usedGroup);
        usedGroup = usedGroup.stream().filter(x -> x.getLocationId() == finalTempLocationId).collect(Collectors.toList());
        if (usedGroup.isEmpty()) {
            throw new NoSingleLocationFoundException();
        } else {
            return usedGroup;
        }
    }
}
