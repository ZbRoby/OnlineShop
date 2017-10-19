package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Location;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT LOCATION_ID FROM PRODUCTS_LOCATIONS WHERE PRODUCT_ID=?1 order by QUANTITY ASC Limit 1", nativeQuery = true)
    Long findOneLocationIdWithLowestQuantityForProduct(Long idProduct);

    @Query(value = "SELECT LOCATION_ID FROM PRODUCTS_LOCATIONS WHERE PRODUCT_ID=?1 order by QUANTITY DESC Limit 1", nativeQuery = true)
    Long findOneLocationIdWithHighestQuantityForProduct(Long idProduct);

}
