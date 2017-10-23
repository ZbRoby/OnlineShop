package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductsLocations;

import java.util.List;
import java.util.Set;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    List<Product> findAllByPriceGreaterThanEqual(double price);

    List<Product> findAllByPriceLessThanEqual(double price);

    @Query(value = "SELECT COALESCE( (SELECT sum(QUANTITY) FROM PRODUCTS_LOCATIONS where PRODUCT_ID=?1 GROUP BY PRODUCT_ID), 0)", nativeQuery = true)
    Long getQuantityForProduct(Long id);

    @Query(value = "select pl from ProductsLocations pl where pl.id.productId in :set order by pl.id.locationId")
    List<ProductsLocations> findAllProductsLocationsInSet(@Param("set") Set<Long> productIdList);

    @Modifying
    @Query(value = "UPDATE PRODUCTS_LOCATIONS SET QUANTITY=QUANTITY-?3 WHERE LOCATION_ID=?1 AND PRODUCT_ID=?2", nativeQuery = true)
    void changeTheQuantity(Long locationId, Long productId, Long quantity);


}
