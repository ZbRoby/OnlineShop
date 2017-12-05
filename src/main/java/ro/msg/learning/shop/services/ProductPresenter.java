package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.entities.enums.ProductStatus;
import ro.msg.learning.shop.models.ShelfProduct;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@Service
public class ProductPresenter {

    private final ProductRepository productRepository;

    @Autowired
    public ProductPresenter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private void setStatus(ShelfProduct shelfProduct, List<ProductsLocations> productsLocations) {
        if (productsLocations.stream().mapToLong(ProductsLocations::getQuantity).sum() == 0) {
            shelfProduct.setProductStatus(ProductStatus.OUT_OF_STOCK);
        } else {
            shelfProduct.setProductStatus(ProductStatus.IN_STOCK);
        }
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN')")
    public List<ShelfProduct> getProductList() {
        ArrayList<ShelfProduct> shelfProducts = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            shelfProducts.add(new ShelfProduct(product));
        }
        shelfProducts.forEach(x -> setStatus(x, productRepository.findAllProductsLocationsWithProductId(x.getProduct().getId())));
        return shelfProducts;
    }
}
