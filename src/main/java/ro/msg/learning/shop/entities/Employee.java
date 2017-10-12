package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
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
@Table(name = "EMPLOYEES")
@Entity
@ToString(doNotUseGetters = true)
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("FirstName")
    @Column(name = "First_Name", nullable = false, unique = false)
    private String firstName;
    @JsonProperty("LastName")
    @Column(name = "Last_Name", nullable = false, unique = false)
    private String lastName;
    @JsonProperty("Username")
    @Column(name = "Username", nullable = false, unique = true)
    private String username;
    @JsonIgnore
    @Column(name = "Password", nullable = false, unique = false)
    private String password;
    @JsonProperty("HomePhone")
    @Column(name = "Home_Phone", nullable = false, unique = false)
    private String homePhone;
    @Enumerated(EnumType.STRING)
    @JsonProperty("Title")
    @Column(name = "Title", nullable = false, unique = false)
    private Title title;


    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Order> orders = new ArrayList<>();

    public Employee() {
    }

    public Employee(String firstName, String lastName, String username, String password, String homePhone, Title title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.homePhone = homePhone;
        this.title = title;
    }

    public void addOrder(Order order){
        if(order.getEmployee()==null){
            if(!this.getOrders().contains(order))
                this.getOrders().add(order);
            order.setEmployee(this);
        }
    }

    public void removeOrder(Order order){
        if(order.getEmployee()==this){
            if(this.getOrders().contains(order))
                this.getOrders().remove(order);
            order.setEmployee(null);
        }
    }
}
