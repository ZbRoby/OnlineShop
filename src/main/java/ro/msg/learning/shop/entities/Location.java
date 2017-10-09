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
@Table(name = "LOCATIONS")
@Entity
@ToString(exclude = {"productsLocations", "address"})
public class Location implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JsonIgnore
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private List<ProductsLocations> productsLocations = new ArrayList<>();

    public Location() {
    }

    public Location(Address address) {
        this.address = address;
    }

    public void addProduct(Product product, long quantity) {
        if (!productsLocations.stream().anyMatch(x -> x.getProduct() == product)) {
            ProductsLocations temp = new ProductsLocations();
            temp.setLocation(this);
            temp.setProduct(product);
            temp.setLocationId(this.getId());
            temp.setProductId(product.getId());
            temp.setQuantity(quantity);
        }

    }

    public void removeProduct(Product product) {
        productsLocations.stream().filter(x -> x.getProduct() == product).forEach(productsLocations::remove);
    }

    public Long getQuantity(Product product) {
        final Optional<ProductsLocations> temp = productsLocations.stream().filter(x -> x.getProduct() == product).findFirst();
        if (temp.isPresent()) {
            return temp.get().getQuantity();
        } else {
            return null;
        }
    }
}
