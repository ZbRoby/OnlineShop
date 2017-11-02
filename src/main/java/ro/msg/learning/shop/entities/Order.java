package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Table(name = "ORDERS")
@Entity
@ToString(doNotUseGetters = true)
public class Order implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("ShippedDate")
    @Column(name = "Shipped_Date", nullable = true, unique = false)
    private Date shippedDate;
    @JsonProperty("OrderDate")
    @Column(name = "Order_Date", nullable = false, unique = false)
    private Date orderDate;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Employee employee;

    @OneToOne
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> ordersDetails = new ArrayList<>();

    public Order() {
    }

    public Order(Date shippedDate, Date orderDate, Customer customer, Employee employee, Address address) {
        this.shippedDate = shippedDate;
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
        this.address = address;
    }

    public void addOrderDetail(OrderDetails orderDetails) {
        if (orderDetails.getOrder() == null) {
            if (!this.getOrdersDetails().contains(orderDetails)) {
                this.getOrdersDetails().add(orderDetails);
            }
            orderDetails.setOrder(this);
        }
    }

    public void removeOrderDetail(OrderDetails orderDetails) {
        if (orderDetails.getOrder() == this) {
            if (this.getOrdersDetails().contains(orderDetails)) {
                this.getOrdersDetails().remove(orderDetails);
            }
            orderDetails.setOrder(null);
        }
    }

}
