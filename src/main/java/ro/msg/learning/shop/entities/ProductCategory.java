package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
public class ProductCategory {

    @JsonProperty("ID")
    private long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("MainCategoryId")
    private Long mainCategoryId;

    public ProductCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductCategory(long id, String name, Long mainCategoryId) {
        this.id = id;
        this.name = name;
        this.mainCategoryId = mainCategoryId;
    }
}
