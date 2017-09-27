package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data()
@Table(name = "CUSTOMERS")
@Entity
public class Customer {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("Username")
    private String username;
    @JsonIgnore
    private String password;
    @JsonProperty("Address")
    private String address;


    @OneToMany(mappedBy = "customer")
    @JsonProperty("Orders")
    private List<Order> orders;

    public Customer() {

    }

    public Customer(String firstName, String lastName, String username, String password, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.address = address;
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
