package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Entity
@Table(name = "PRODUCT_CATEGORIES")
public class ProductCategory {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("Name")
    private String name;

    @OneToOne
    @JsonProperty("Main_Category_ID")
    private ProductCategory mainCategory;


    @OneToMany(mappedBy = "category")
    private List<Product> products =new ArrayList<>();

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory(String name, ProductCategory mainCategory) {
        this.name = name;
        this.mainCategory = mainCategory;
    }

    public void addProduct(Product product){
        if(product.getCategory()==null){
            if(!this.products.contains(product))
                this.products.add(product);
            product.setCategory(this);
        }
    }

    public void removeProduct(Product product){
        if(product.getCategory()==this) {
            if(this.products.contains(product))
                this.products.remove(product);
            product.setCategory(null);
        }
    }


}
