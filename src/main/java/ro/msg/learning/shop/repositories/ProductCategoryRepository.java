package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.ProductCategory;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
}

