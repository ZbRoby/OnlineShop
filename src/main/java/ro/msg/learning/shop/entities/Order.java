package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Table(name = "ORDERS")
@Entity
public class Order {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    private long id;

    @JsonProperty("ShippedDate")
    private Date shippedDate;

    @JsonProperty("OrderDate")
    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("Customer_ID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("Employee_ID")
    private Employee employee;

    @JsonProperty("OrderDetails")
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> ordersDetails=new ArrayList<>();

    public Order() {
    }

    public Order(Date shippedDate, Date orderDate, Customer customer, Employee employee) {
        this.shippedDate = shippedDate;
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
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
