package ro.msg.learning.shop.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.enums.ProductStatus;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public interface ShelfProductMixin {

    @JsonProperty("Product")
    Product getProduct();

    @JsonProperty("Status")
    ProductStatus getProductStatus();
}
