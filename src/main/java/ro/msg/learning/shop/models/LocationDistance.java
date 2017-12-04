package ro.msg.learning.shop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entities.ProductsLocations;
import ro.msg.learning.shop.models.gdistance.Element;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDistance {

    private ProductsLocations location;
    private Element distance;

}
