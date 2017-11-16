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
@Table(name = "LOCATIONS")
public class Location implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "location")
    private List<ProductsLocations> productsLocations = new ArrayList<>();

    public Location(Address address) {
        this.address = address;
    }

    public void addProduct(Product product, long quantity) {
        if (productsLocations.stream().noneMatch(x -> x.getProduct() == product)) {
            ProductsLocations temp = new ProductsLocations();
            temp.setLocation(this);
            temp.setProduct(product);
            temp.setLocationId(this.getId());
            temp.setProductId(product.getId());
            temp.setQuantity(quantity);
            getProductsLocations().add(temp);
        }

    }

    public void removeProduct(Product product) {
        getProductsLocations().stream().filter(x -> x.getProduct() == product).forEach(getProductsLocations()::remove);
    }

    public Long getQuantity(Product product) {
        final Optional<ProductsLocations> temp = getProductsLocations().stream().filter(x -> x.getProduct() == product).findFirst();
        return temp.map(ProductsLocations::getQuantity).orElse(null);
    }

    public void setQuantity(Product product, long quantity) {
        final Optional<ProductsLocations> temp = getProductsLocations().stream().filter(x -> x.getProduct() == product).findFirst();
        temp.ifPresent(p -> p.setQuantity(quantity));
    }
}
