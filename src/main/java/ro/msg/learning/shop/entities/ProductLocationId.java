package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@Embeddable
public class ProductLocationId implements Serializable {

    @Column(name = "Product_ID")
    private Long productId;
    @Column(name = "Location_ID")
    private Long locationId;

    public ProductLocationId(Long productId, Long locationId) {
        this.productId = productId;
        this.locationId = locationId;
    }
}
