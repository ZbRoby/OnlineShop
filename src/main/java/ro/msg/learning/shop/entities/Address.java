package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@ToString(doNotUseGetters = true, exclude = "customers")
@EqualsAndHashCode(doNotUseGetters = true, exclude = "customers")
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

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private List<Customer> customers = new ArrayList<>();


    public Address() {
    }

    public Address(String country, String city, String street, String zipCode, String other) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.other = other;
    }

    public void addCustomer(Customer customer) {
        if (customer.getAddress() == null) {
            if (!this.getCustomers().contains(customer)) {
                this.getCustomers().add(customer);
            }
            customer.setAddress(this);
        }
    }

    public void removeCustomer(Customer customer) {
        if (customer.getAddress() == this) {
            if (this.getCustomers().contains(customer)) {
                this.getCustomers().remove(customer);
            }
            customer.setAddress(null);
        }
    }

}
