package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@Entity
@Table(name = "PRODUCT_CATEGORIES")
@ToString(exclude = {"products", "mainCategory"})
public class ProductCategory implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;
    @JsonProperty("Name")
    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @OneToOne
    @JsonProperty("MainCategoryID")
    private ProductCategory mainCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products =new ArrayList<>();

    public ProductCategory() {
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
