package ro.msg.learning.shop.model;

import ro.msg.learning.shop.entities.ProductsLocations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 * productIdLocationIdQunantityList
 */
public class PLQList extends ListParam<ProductLocationQuantity> {

    public PLQList() {
        this.setList(new ArrayList<>());
    }

    public PLQList(List<ProductsLocations> productsLocationsList) {
        List<ProductLocationQuantity> plqList = new ArrayList<>();
        for (ProductsLocations productsLocations : productsLocationsList) {
            plqList.add(new ProductLocationQuantity(productsLocations));
        }
        this.setList(plqList);
    }

    public Map<Long, Long> getMap() {
        HashMap<Long, Long> ret = new HashMap<>();
        for (ProductLocationQuantity productLocationQuantity : this.getList()) {
            ret.put(productLocationQuantity.getProductId(), productLocationQuantity.getQuantity());
        }
        return ret;
    }

    @Override
    public String toString() {
        return "PLQList{" + this.getList() + "}";
    }
}
