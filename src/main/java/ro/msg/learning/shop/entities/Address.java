package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@Data
@Entity
@Table(name = "Addresses")
public class Address implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("Country")
    @Column(name = "Country", nullable = false, unique = false)
    private String country;
    @JsonProperty("City")
    @Column(name = "City", nullable = false, unique = false)
    private String city;
    @JsonProperty("Street")
    @Column(name = "Street", nullable = false, unique = false)
    private String street;
    @JsonProperty("ZipCode")
    @Column(name = "Zip_Code", nullable = true, unique = false)
    private String zipCode;
    @JsonProperty("Other")
    @Column(name = "Other", nullable = true, unique = false)
    private String other;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Location> locations = new ArrayList<>();

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public Address() {
    }

    public Address(String country, String city, String street, String zipCode, String other) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.other = other;
    }

    public void addOrder(Order order) {
        if (order.getAddress() == null) {
            if (!this.orders.contains(order)) {
                this.orders.add(order);
            }
            order.setAddress(this);
        }
    }

    public void removeOrder(Order order) {
        if (order.getAddress() == this) {
            if (this.orders.contains(order)) {
                this.orders.remove(order);
            }
            order.setAddress(null);
        }
    }

    public void addLocation(Location location) {
        if (location.getAddress() == null) {
            if (!this.locations.contains(location)) {
                this.locations.add(location);
            }
            location.setAddress(this);
        }
    }

    public void removeLocation(Location location) {
        if (location.getAddress() == this) {
            if (this.locations.contains(location)) {
                this.locations.remove(location);
            }
            location.setAddress(null);
        }
    }
}
