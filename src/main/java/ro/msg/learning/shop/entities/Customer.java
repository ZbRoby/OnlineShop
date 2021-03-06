package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data()
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true, exclude = "orders")
@ToString(doNotUseGetters = true, exclude = "orders")
@Entity
@Table(name = "CUSTOMERS")
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Column(name = "FirstName", nullable = false, unique = false)
    private String firstName;
    @Column(name = "LastName", nullable = false, unique = false)
    private String lastName;

    @OneToOne
    private User user;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Customer(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
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
