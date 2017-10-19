package ro.msg.learning.shop.services.strategy;

import ro.msg.learning.shop.entities.ProductsLocations;

import java.util.List;
import java.util.Map;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public interface QuantityStrategy {

    List<ProductsLocations> getLocations(Map<Long, Long> productList, List<ProductsLocations> locationAndProduct);

}
