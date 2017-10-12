package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Entity
@Table(name = "PRODUCTS_LOCATIONS")
@ToString(doNotUseGetters = true)
public class ProductsLocations implements Serializable {

    @Id
    @Column(name = "Product_ID")
    @JsonProperty("ProductID")
    private Long productId;
    @Id
    @Column(name = "Location_ID")
    @JsonProperty("LocationID")
    private Long locationId;
    @Column(name = "Quantity", nullable = true, unique = false)
    @JsonProperty("Quantity")
    private long quantity;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "Product_ID", referencedColumnName = "ID")
    private Product product;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "Location_ID", referencedColumnName = "ID")
    private Location location;

    public ProductsLocations() {
    }

    public ProductsLocations(Long productId, Long locationId, long quantity, Product product, Location location) {
        this.productId = productId;
        this.locationId = locationId;
        this.quantity = quantity;
        this.product = product;
        this.location = location;
    }
}
