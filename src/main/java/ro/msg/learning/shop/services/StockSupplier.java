package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.*;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Service
public class StockSupplier {

    private final ProductRepository productRepository;

    @Autowired
    public StockSupplier(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addStock(List<Long> productIdList, List<Long> quantityIdList) {
        HashMap<Long, Long> productQuantity = new HashMap<>();
        for (int i = 0; i < productIdList.size(); i++) {
            productQuantity.put(productIdList.get(i), quantityIdList.get(i));
        }
        addStock(productQuantity);
    }

    @Transactional
    public void addStock(Map<Long, Long> productQuantity) {
        List<ProductsLocations> productLocations = productRepository.findAllProductsLocationsInSet(productQuantity.keySet());
        long count = productLocations.stream().mapToLong(ProductsLocations::getProductId).distinct().count();
        if (productQuantity.keySet().size() != count) {
            throw new ProductNotFoundException();
        }

        productLocations.sort(Comparator.comparingLong(ProductsLocations::getQuantity));
        for (ProductsLocations productLocation : productLocations) {
            if (productQuantity.containsKey(productLocation.getProductId())) {
                productLocation.setQuantity(productLocation.getQuantity() + productQuantity.get(productLocation.getProductId()));
                productQuantity.remove(productLocation.getProductId());
            }
        }

    }
}

