package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Entity
@Table(name = "PRODUCTS")
@ToString(doNotUseGetters = true, exclude = "productsLocations")
public class Product implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("Name")
    @Column(name = "Name", nullable = false, unique = true)
    private String name;
    @JsonProperty("SupplierName")
    @Column(name = "Supplier_Name", nullable = false, unique = false)
    private String supplierName;
    @JsonProperty("Description")
    @Column(name = "Description", nullable = true, unique = false)
    private String description;
    @JsonProperty("CurrencyCode")
    @Column(name = "Currency_Code", nullable = false, unique = false)
    private String currencyCode;
    @JsonProperty("Price")
    @Column(name = "Price", nullable = false, unique = false)
    private double price;

    @OneToOne
    private ProductDetails productDetails;

    @ManyToOne
    private ProductCategory category;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductsLocations> productsLocations = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String supplierName, String description, String currencyCode, double price, ProductDetails productDetails, ProductCategory category) {
        this.name = name;
        this.supplierName = supplierName;
        this.description = description;
        this.currencyCode = currencyCode;
        this.price = price;
        this.productDetails = productDetails;
        this.category = category;
    }

    public void addLocation(Location location, long quantity) {
        if (!getProductsLocations().stream().anyMatch(x -> x.getLocation() == location)) {
            ProductsLocations temp = new ProductsLocations();
            temp.setLocation(location);
            temp.setProduct(this);
            temp.setLocationId(location.getId());
            temp.setProductId(this.getId());
            temp.setQuantity(quantity);
            getProductsLocations().add(temp);
        }
    }

    public void removeLocation(Location location) {
        this.getProductsLocations().stream().filter(x -> x.getLocation() == location).forEach(getProductsLocations()::remove);
    }

    public Long getQuantity(Location location) {
        final Optional<ProductsLocations> temp = getProductsLocations().stream().filter(x -> x.getLocation() == location).findFirst();
        if (temp.isPresent()) {
            return temp.get().getQuantity();
        } else {
            return null;
        }
    }

    public void setQuantity(Location location, long quantity) {
        final Optional<ProductsLocations> temp = getProductsLocations().stream().filter(x -> x.getLocation() == location).findFirst();
        if (temp.isPresent()) {
            temp.get().setQuantity(quantity);
        }
    }
}
