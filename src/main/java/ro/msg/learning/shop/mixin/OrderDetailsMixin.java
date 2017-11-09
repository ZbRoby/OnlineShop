package ro.msg.learning.shop.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.Product;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface OrderDetailsMixin {

    @JsonProperty("Id")
    long getId();

    @JsonProperty("Quantity")
    Long getQuantity();

    @JsonProperty("Discount")
    double getDiscount();

    @JsonProperty("UnitPrice")
    double getUnitPrice();

    @JsonProperty("Product")
    Product getProduct();

    @JsonIgnore
    Order getOrder();
}
