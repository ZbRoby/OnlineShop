package ro.msg.learning.shop.entities;

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
    @Column(name = "ID")
    private long id;
    @Column(name = "Country", nullable = false, unique = false)
    private String country;
    @Column(name = "City", nullable = false, unique = false)
    private String city;
    @Column(name = "Street", nullable = false, unique = false)
    private String street;
    @Column(name = "Zip_Code", nullable = true, unique = false)
    private String zipCode;
    @Column(name = "Other", nullable = true, unique = false)
    private String other;

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
