package ro.msg.learning.shop.model;

import lombok.Data;
import ro.msg.learning.shop.entities.Address;

import java.sql.Date;
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

    public OrderInput() {
    }

    public OrderInput(OrderInput orderInput) {
        this.productMap = orderInput.getProductMap();
        this.date = orderInput.getDate();
        this.address = orderInput.getAddress();
    }
}
