package ro.msg.learning.shop.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.Role;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface UserMixin {

    @JsonProperty("Id")
    long getId();

    @JsonProperty("Username")
    String getUsername();

    @JsonIgnore
    String getPassword();

    @JsonProperty("Roles")
    List<Role> getRoles();
}
