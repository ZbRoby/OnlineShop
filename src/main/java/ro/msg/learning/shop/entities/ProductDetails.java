package ro.msg.learning.shop.entities;

import lombok.Data;
import ro.msg.learning.shop.entities.enums.DistanceUnit;
import ro.msg.learning.shop.entities.enums.MassUnit;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@Data
@Entity
@Table(name = "PRODUCT_DETAILS")
public class ProductDetails implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "Mass_Unit", nullable = true, unique = false)
    private MassUnit massUnit;
    @Column(name = "Mass", nullable = true, unique = false)
    private double mass;
    @Enumerated(EnumType.STRING)
    @Column(name = "Distance_Unit", nullable = true, unique = false)
    private DistanceUnit distanceUnit;
    @Column(name = "Width", nullable = true, unique = false)
    private double width;
    @Column(name = "Height", nullable = true, unique = false)
    private double height;
    @Column(name = "Depth", nullable = true, unique = false)
    private double depth;

    public ProductDetails() {
    }

    public ProductDetails(MassUnit massUnit, double mass, DistanceUnit distanceUnit, double width, double height, double depth) {
        this.massUnit = massUnit;
        this.mass = mass;
        this.distanceUnit = distanceUnit;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
}
