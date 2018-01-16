package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "Addresses")
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

    public Address(String country, String city, String street, String zipCode, String other) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.other = other;
    }

}
