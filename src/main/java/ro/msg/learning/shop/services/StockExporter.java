package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@Service
public class StockExporter {

    private final ProductRepository productRepository;

    @Autowired
    public StockExporter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ProductsLocations> getStockOfLocation(long locationId) {
        return productRepository.findAllProductsLocationsWithLocationId(locationId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ProductsLocations> getStock() {
        return productRepository.findAllProductsLocations();
    }
}
