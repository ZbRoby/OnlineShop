package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ro.msg.learning.shop.entities.enums.DistanceUnit;
import ro.msg.learning.shop.entities.enums.MassUnit;

import javax.persistence.*;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("Quantity")
    private short quantity;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("SupplierName")
    private String supplierName;
    @JsonProperty("Description")
    private String description;
    @Enumerated(EnumType.STRING)@JsonProperty("MassUnit")
    private MassUnit massUnit;
    @JsonProperty("Mass")
    private double mass;
    @Enumerated(EnumType.STRING)@JsonProperty("DistanceUnit")
    private DistanceUnit distanceUnit;
    @JsonProperty("Width")
    private double width;
    @JsonProperty("Height")
    private double height;
    @JsonProperty("Depth")
    private double depth;
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    @JsonProperty("Price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("Category_ID")
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("Location_ID")
    private Location location;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(short quantity, String name, String supplierName, String description, String currencyCode, double price, ProductCategory category) {
        this.quantity = quantity;
        this.name = name;
        this.supplierName = supplierName;
        this.description = description;
        this.currencyCode = currencyCode;
        this.price = price;
        this.category = category;
    }

    public void setDimensions(DistanceUnit distanceUnit, double width, double height, double depth) {
        this.distanceUnit = distanceUnit;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public void setWeight(MassUnit massUnit, double mass) {
        this.massUnit = massUnit;
        this.mass = mass;
    }
}
