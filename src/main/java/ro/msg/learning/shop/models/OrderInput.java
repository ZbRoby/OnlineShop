package ro.msg.learning.shop.models;

import lombok.Data;
import ro.msg.learning.shop.entities.Address;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
public class OrderInput {
    private Map<Long, Long> productMap;
    private Date date;
    private Address address;

    public OrderInput(Map<Long, Long> productMap, Date date, Address address) {
        this.productMap = productMap;
        this.date = date;
        this.address = address;
    }

    public OrderInput(List<Long> productList, List<Long> quantityList, Date date, Address address) {
        this.productMap = new HashMap<>();
        for (int i = 0; i < productList.size(); i++) {
            this.productMap.put(productList.get(i), quantityList.get(i));
        }
        this.date = date;
        this.address = address;
    }

    public OrderInput() {
    }
}
