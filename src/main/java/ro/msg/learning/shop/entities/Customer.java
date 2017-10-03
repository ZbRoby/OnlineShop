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

@Data()
@Table(name = "CUSTOMERS")
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("FirstName")
    @Column(name = "FirstName", nullable = false, unique = false)
    private String firstName;
    @JsonProperty("LastName")
    @Column(name = "LastName", nullable = false, unique = false)
    private String lastName;
    @JsonProperty("Username")
    @Column(name = "Username", nullable = false, unique = true)
    private String username;
    @JsonIgnore
    @Column(name = "Password", nullable = false, unique = false)
    private String password;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public void addOrder(Order order){
        if(order.getCustomer()==null){
            if(!this.orders.contains(order))
                this.orders.add(order);
            order.setCustomer(this);
        }
    }

    public void removeOrder(Order order){
        if(order.getCustomer()==this){
            if(this.orders.contains(order))
                this.orders.remove(order);
            order.setCustomer(null);
        }
    }
}
