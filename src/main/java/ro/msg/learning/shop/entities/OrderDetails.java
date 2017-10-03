package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Table(name = "ORDER_DETAILS")
@Entity
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("Quantity")
    @Column(name = "Quantity", nullable = true, unique = false)
    private short quantity;
    @JsonProperty("Discount")
    @Column(name = "Discount", nullable = false, unique = false)
    private double discount;
    @JsonProperty("UnitPrice")
    @Column(name = "UnitPrice", nullable = false, unique = false)
    private double unitPrice;

    @OneToOne
    @JsonProperty("ProductID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("Order_ID")
    private Order order;

    public OrderDetails() {
    }

    public OrderDetails(short quantity, double discount, double unitPrice, Product product) {
        this.quantity = quantity;
        this.discount = discount;
        this.unitPrice = unitPrice;
        this.product = product;
    }

}
