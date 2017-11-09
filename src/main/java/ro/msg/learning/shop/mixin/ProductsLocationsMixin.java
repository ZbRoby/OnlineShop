package ro.msg.learning.shop.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Product;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface ProductsLocationsMixin {
    @JsonIgnore
    long getId();

    @JsonProperty("ProductId")
    Long getProductId();

    @JsonProperty("LocationId")
    Long getLocationId();

    @JsonProperty("Quantity")
    long getQuantity();

    @JsonProperty("Product")
    Product getProduct();

    @JsonProperty("Location")
    Location getLocation();

}
