package ro.msg.learning.shop.utils;

import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.ProductsLocations;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public interface DistanceCalculator {

    List<ProductsLocations> sortLocations(Address destination, List<ProductsLocations> origins);
}
