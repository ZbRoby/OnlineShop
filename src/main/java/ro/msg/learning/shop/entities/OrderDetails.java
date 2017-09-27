package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Table(name = "ORDER_DETAILS")
@Entity
public class OrderDetails {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    private long id;

    @JsonProperty("Quantity")
    private short quantity;


    @JsonProperty("Discount")
    private double discount;

    @JsonProperty("UnitPrice")
    private double unitPrice;

    @OneToOne
    @JsonProperty("Product_ID")
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
