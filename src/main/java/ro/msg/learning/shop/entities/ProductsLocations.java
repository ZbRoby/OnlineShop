package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Entity
@Table(name = "PRODUCTS_LOCATIONS")
@ToString(doNotUseGetters = true, exclude = {"product", "location"})
@EqualsAndHashCode(doNotUseGetters = true, exclude = {"product", "location"})
public class ProductsLocations implements Serializable {
    @EmbeddedId
    private ProductLocationId id = new ProductLocationId();

    private long quantity;

    @ManyToOne
    @JoinColumn(name = "Product_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "Location_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private Location location;

    public ProductsLocations() {
    }

    public ProductsLocations(Long productId, Long locationId, long quantity, Product product, Location location) {
        this.id = new ProductLocationId(productId, locationId);
        this.quantity = quantity;
        this.product = product;
        this.location = location;

    }

    public ProductsLocations(Long productId, Long locationId, long quantity) {
        this.id = new ProductLocationId(productId, locationId);
        this.quantity = quantity;
    }

    public Long getProductId() {
        return this.id.getProductId();
    }

    public void setProductId(Long productId) {
        this.id.setProductId(productId);
    }

    public Long getLocationId() {
        return this.id.getLocationId();
    }

    public void setLocationId(Long locationId) {
        this.id.setLocationId(locationId);
    }

}


