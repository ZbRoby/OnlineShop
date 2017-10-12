package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Product;

import java.util.List;

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

}
