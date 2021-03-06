package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ro.msg.learning.shop.entities.enums.Title;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true, exclude = "orders")
@ToString(doNotUseGetters = true, exclude = "orders")
@Entity
@Table(name = "EMPLOYEES")
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Column(name = "First_Name", nullable = false, unique = false)
    private String firstName;
    @Column(name = "Last_Name", nullable = false, unique = false)
    private String lastName;
    @Column(name = "Home_Phone", nullable = false, unique = false)
    private String homePhone;
    @Enumerated(EnumType.STRING)
    @Column(name = "Title", nullable = false, unique = false)
    private Title title;

    @OneToOne
    private User user;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders = new ArrayList<>();

    public Employee(String firstName, String lastName, User user, String homePhone, Title title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.homePhone = homePhone;
        this.title = title;
    }

    public void addOrder(Order order) {
        if (order.getEmployee() == null) {
            if (!this.getOrders().contains(order)) {
                this.getOrders().add(order);
            }
            order.setEmployee(this);
        }
    }

    public void removeOrder(Order order) {
        if (order.getEmployee() == this) {
            if (this.getOrders().contains(order)) {
                this.getOrders().remove(order);
            }
            order.setEmployee(null);
        }
    }
}
