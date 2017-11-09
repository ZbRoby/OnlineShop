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

@Data()
@Table(name = "CUSTOMERS")
@Entity
@ToString(doNotUseGetters = true, exclude = "orders")
@EqualsAndHashCode(doNotUseGetters = true, exclude = "orders")
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Column(name = "FirstName", nullable = false, unique = false)
    private String firstName;
    @Column(name = "LastName", nullable = false, unique = false)
    private String lastName;
    @Column(name = "Username", nullable = false, unique = true)
    private String username;
    @Column(name = "Password", nullable = false, unique = false)
    private String password;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public void addOrder(Order order) {
        if (order.getCustomer() == null) {
            if (!this.getOrders().contains(order)) {
                this.getOrders().add(order);
            }
            order.setCustomer(this);
        }
    }

    public void removeOrder(Order order) {
        if (order.getCustomer() == this) {
            if (this.getOrders().contains(order)) {
                this.getOrders().remove(order);
            }
            order.setCustomer(null);
        }
    }
}
