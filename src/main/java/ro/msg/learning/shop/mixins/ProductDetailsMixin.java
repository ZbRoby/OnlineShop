package ro.msg.learning.shop.mixins;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.enums.DistanceUnit;
import ro.msg.learning.shop.entities.enums.MassUnit;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface ProductDetailsMixin {

    @JsonProperty("Id")
    long getId();

    @JsonProperty("MassUnit")
    MassUnit getMassUnit();

    @JsonProperty("Mass")
    double getMass();

    @JsonProperty("DistanceUnit")
    DistanceUnit getDistanceUnit();

    @JsonProperty("Width")
    double getWidth();

    @JsonProperty("Height")
    double getHeight();

    @JsonProperty("Depth")
    double getDepth();
}
