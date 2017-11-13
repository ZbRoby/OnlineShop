package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.enums.ProductStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelfProduct {

    private Product product;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    public ShelfProduct(Product product) {
        this.product = product;
    }

}
