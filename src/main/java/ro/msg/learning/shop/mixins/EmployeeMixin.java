package ro.msg.learning.shop.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.entities.enums.Title;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public interface EmployeeMixin {


    @JsonProperty("Id")
    long getId();

    @JsonProperty("FirstName")
    String getFirstName();

    @JsonProperty("LastName")
    String getLastName();

    @JsonProperty("User")
    User getUser();

    @JsonProperty("HomePhone")
    String getHomePhone();

    @JsonProperty("Title")
    Title getTitle();

    @JsonProperty("Address")
    Address getAddress();

    @JsonIgnore
    List<Order> getOrders();
}
