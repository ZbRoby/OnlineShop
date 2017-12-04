package ro.msg.learning.shop.models.gdistance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.models.gdistance.enums.TopGoogleStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleDistance {

    @JsonProperty("destination_addresses")
    private List<String> destinationAddresses = new ArrayList<>();
    @JsonProperty("origin_addresses")
    private List<String> originAddresses = new ArrayList<>();
    private List<Row> rows = new ArrayList<>();
    private TopGoogleStatus status;
}
