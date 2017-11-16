package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true, exclude = "productsLocations")
@ToString(doNotUseGetters = true, exclude = "productsLocations")
@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String supplierName;
    private String description;
    @Column(name = "Currency_Code", nullable = false)
    private String currencyCode;
    @Column(name = "Price", nullable = false)
    private double price;

    @OneToOne
    private ProductDetails productDetails;

    @ManyToOne
    private ProductCategory category;

    @OneToMany(mappedBy = "product")
    private List<ProductsLocations> productsLocations = new ArrayList<>();

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
        if (getProductsLocations().stream().noneMatch(x -> x.getLocation() == location)) {
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
        return temp.map(ProductsLocations::getQuantity).orElse(null);
    }

    public void setQuantity(Location location, long quantity) {
        final Optional<ProductsLocations> temp = getProductsLocations().stream().filter(x -> x.getLocation() == location).findFirst();
        temp.ifPresent(prodLocations -> prodLocations.setQuantity(quantity));
    }
}
