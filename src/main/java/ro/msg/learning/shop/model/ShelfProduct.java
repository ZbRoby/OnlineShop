package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.enums.ProductStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@JsonSerialize
public class ShelfProduct {

    @JsonProperty("Product")
    private Product product;
    @Enumerated(EnumType.STRING)
    @JsonProperty("Status")
    private ProductStatus productStatus;

    public ShelfProduct(Product product) {
        this.product = product;
    }

    public ShelfProduct(Product product, ProductStatus productStatus) {
        this.product = product;
        this.productStatus = productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
