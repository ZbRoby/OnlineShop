package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entities.enums.DistanceUnit;
import ro.msg.learning.shop.entities.enums.MassUnit;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_DETAILS")
public class ProductDetails implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private MassUnit massUnit;
    private double mass;
    @Enumerated(EnumType.STRING)
    private DistanceUnit distanceUnit;
    private double width;
    private double height;
    private double depth;

    public ProductDetails(MassUnit massUnit, double mass, DistanceUnit distanceUnit, double width, double height, double depth) {
        this.massUnit = massUnit;
        this.mass = mass;
        this.distanceUnit = distanceUnit;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
}
