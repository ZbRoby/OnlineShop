package ro.msg.learning.shop.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.Customer;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface AddressMixin {

    @JsonProperty("Id")
    long getId();

    @JsonProperty("Country")
    String getCountry();

    @JsonProperty("City")
    String getCity();

    @JsonProperty("Street")
    String getStreet();

    @JsonProperty("ZipCode")
    String getZipCode();

    @JsonProperty("Other")
    String getOther();

    @JsonIgnore
    List<Customer> getCustomers();

}
