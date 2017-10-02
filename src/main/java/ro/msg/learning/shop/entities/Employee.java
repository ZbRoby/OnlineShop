package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ro.msg.learning.shop.entities.enums.Title;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Table(name = "EMPLOYEES")
@Entity
public class Employee {

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
    @JsonProperty("Photo")
    private String photo;
    @JsonProperty("HomePhone")
    private String homePhone;
    @Enumerated(EnumType.STRING)@JsonProperty("Title")
    private Title title;


    @OneToMany(mappedBy = "employee")
    @JsonProperty("Orders")
    private List<Order> orders = new ArrayList<>();

    public Employee() {
    }

    public Employee(String firstName, String lastName, String username, String password, String photo, String homePhone, Title title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.homePhone = homePhone;
        this.title = title;
    }

    public void addOrder(Order order){
        if(order.getEmployee()==null){
            if(!this.orders.contains(order))
                this.orders.add(order);
            order.setEmployee(this);
        }
    }

    public void removeOrder(Order order){
        if(order.getEmployee()==this){
            if(this.orders.contains(order))
                this.orders.remove(order);
            order.setEmployee(null);
        }
    }
}
