package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Table(name = "ORDER_DETAILS")
@Entity
@ToString(doNotUseGetters = true, exclude = "order")
@EqualsAndHashCode(doNotUseGetters = true, exclude = "order")
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("Quantity")
    @Column(name = "Quantity", nullable = true, unique = false)
    private Long quantity;
    @JsonProperty("Discount")
    @Column(name = "Discount", nullable = false, unique = false)
    private double discount;
    @JsonProperty("UnitPrice")
    @Column(name = "UnitPrice", nullable = false, unique = false)
    private double unitPrice;

    @OneToOne
    private Product product;

    @JsonIgnore
    @ManyToOne
    private Order order;

    public OrderDetails() {
    }

    public OrderDetails(Long quantity, double discount, Product product) {
        this.quantity = quantity;
        this.discount = discount;
        this.unitPrice = product.getPrice() * (1 - discount / 100);
        this.product = product;
    }

}
