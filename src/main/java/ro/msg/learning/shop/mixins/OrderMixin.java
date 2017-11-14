package ro.msg.learning.shop.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.entities.Employee;
import ro.msg.learning.shop.entities.OrderDetails;

import java.sql.Date;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public interface OrderMixin {

    @JsonProperty("Id")
    long getId();

    @JsonProperty("ShippedDate")
    Date getShippedDate();

    @JsonProperty("OrderDate")
    Date getOrderDate();

    @JsonProperty("Customer")
    Customer getCustomer();

    @JsonProperty("Employee")
    Employee getEmployee();

    @JsonProperty("Address")
    Address getAddress();

    @JsonProperty("OrdersDetails")
    List<OrderDetails> getOrdersDetails();
}
