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

@Data
@Table(name = "ORDERS")
@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("ShippedDate")
    @Column(name = "Shipped_Date", nullable = true, unique = false)
    private java.sql.Date shippedDate;
    @JsonProperty("OrderDate")
    @Column(name = "Order_Date", nullable = false, unique = false)
    private java.sql.Date orderDate;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @ManyToOne
    @JsonIgnore
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> ordersDetails=new ArrayList<>();

    public Order() {
    }

    public Order(java.sql.Date shippedDate, java.sql.Date orderDate, Customer customer, Employee employee, Address address) {
        this.shippedDate = shippedDate;
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
        this.address = address;
    }

    public void addOrderDetail(OrderDetails orderDetails){
        if(orderDetails.getOrder()==null){
            if(!this.ordersDetails.contains(orderDetails))
                this.ordersDetails.add(orderDetails);
            orderDetails.setOrder(this);
        }
    }

    public void removeOrderDetail(OrderDetails orderDetails){
        if(orderDetails.getOrder()==this){
            if(this.ordersDetails.contains(orderDetails))
                this.ordersDetails.remove(orderDetails);
            orderDetails.setOrder(null);
        }
    }

}
