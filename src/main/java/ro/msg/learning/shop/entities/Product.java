package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
public class Product {

    @JsonProperty("ID")
    private long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Category_ID")
    private Long categoryId;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(long id, String name, Long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }
}
