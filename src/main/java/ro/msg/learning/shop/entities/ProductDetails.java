package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
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
@ToString(doNotUseGetters = true)
public class ProductDetails implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @Enumerated(EnumType.STRING)
    @JsonProperty("MassUnit")
    @Column(name = "Mass_Unit", nullable = true, unique = false)
    private MassUnit massUnit;
    @JsonProperty("Mass")
    @Column(name = "Mass", nullable = true, unique = false)
    private double mass;
    @Enumerated(EnumType.STRING)
    @JsonProperty("DistanceUnit")
    @Column(name = "Distance_Unit", nullable = true, unique = false)
    private DistanceUnit distanceUnit;
    @JsonProperty("Width")
    @Column(name = "Width", nullable = true, unique = false)
    private double width;
    @JsonProperty("Height")
    @Column(name = "Height", nullable = true, unique = false)
    private double height;
    @JsonProperty("Depth")
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
