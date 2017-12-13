package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private Date shippedDate;
    @Column(nullable = false)
    private Date orderDate;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Employee employee;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> ordersDetails = new ArrayList<>();

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
