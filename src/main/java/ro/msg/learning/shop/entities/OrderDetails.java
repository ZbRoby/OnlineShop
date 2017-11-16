package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true, exclude = "order")
@ToString(doNotUseGetters = true, exclude = "order")
@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private Long quantity;
    @Column(nullable = false)
    private double discount;
    @Column(nullable = false)
    private double unitPrice;

    @OneToOne
    private Product product;

    @ManyToOne
    private Order order;

    public OrderDetails(Long quantity, double discount, Product product) {
        this.quantity = quantity;
        this.discount = discount;
        this.unitPrice = product.getPrice() * (1 - discount / 100);
        this.product = product;
    }

}
