package ro.msg.learning.shop.models.gdistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.models.gdistance.enums.ElementGoogleStatus;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Element {
    private TimeAndSpace distance;
    private TimeAndSpace duration;
    private ElementGoogleStatus status;
}
