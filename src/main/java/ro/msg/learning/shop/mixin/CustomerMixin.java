package ro.msg.learning.shop.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Order;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface CustomerMixin {

    @JsonProperty("Id")
    long getId();

    @JsonProperty("FirstName")
    String getFirstName();

    @JsonProperty("LastName")
    String getLastName();

    @JsonProperty("Username")
    String getUsername();

    @JsonIgnore
    String getPassword();

    @JsonProperty("Address")
    Address getAddress();

    @JsonIgnore
    List<Order> getOrders();
}
