package ro.msg.learning.shop.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import ro.msg.learning.shop.entities.ProductsLocations;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@Data
public class ProductLocationQuantity {
    @CsvBindByName
    private long productId;
    @CsvBindByName
    private long locationId;
    @CsvBindByName
    private long quantity;

    public ProductLocationQuantity() {
    }

    public ProductLocationQuantity(long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public ProductLocationQuantity(long productId, long locationId, long quantity) {
        this.productId = productId;
        this.locationId = locationId;
        this.quantity = quantity;
    }

    public ProductLocationQuantity(ProductsLocations productLocationQuantity) {
        this.productId = productLocationQuantity.getProductId();
        this.locationId = productLocationQuantity.getLocationId();
        this.quantity = productLocationQuantity.getQuantity();
    }
}
