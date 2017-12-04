package ro.msg.learning.shop.models.gdistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Row {
    List<Element> elements = new ArrayList<>();
}
