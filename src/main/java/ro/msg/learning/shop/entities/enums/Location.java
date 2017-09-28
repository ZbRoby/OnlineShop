package ro.msg.learning.shop.entities.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ro.msg.learning.shop.entities.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Table(name = "LOCATIONS")
@Entity
public class Location {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("City")
    private String city;
    @JsonProperty("Address")
    private String address;

    @OneToMany(mappedBy = "location")
    private List<Product> products = new ArrayList<>();

    public Location() {
    }

    public Location(String country, String city, String address, List<Product> products) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.products = products;
    }

    public void addProduct(Product product) {
        if (product.getLocation() == null) {
            if (!this.products.contains(product)) {
                this.products.add(product);
            }
            product.setLocation(this);
        }
    }

    public void removeProduct(Product product) {
        if (product.getLocation() == this) {
            if (this.products.contains(product)) {
                this.products.remove(product);
            }
            product.setLocation(null);
        }
    }
}
