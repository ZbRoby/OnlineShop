package ro.msg.learning.shop.mixins;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.Address;

import java.sql.Date;
import java.util.Map;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface OrderInputMixin {

    @JsonProperty("ProductMap")
    Map<Long, Long> getProductMap();

    @JsonProperty("Date")
    Date getDate();

    @JsonProperty("Address")
    Address getAddress();
}
