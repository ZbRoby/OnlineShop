package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true, exclude = "products")
@ToString(doNotUseGetters = true, exclude = "products")
@Entity
@Table(name = "PRODUCT_CATEGORIES")
public class ProductCategory implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    private ProductCategory mainCategory;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public ProductCategory(String name, ProductCategory mainCategory) {
        this.name = name;
        this.mainCategory = mainCategory;
    }

    public void addProduct(Product product) {
        if (product.getCategory() == null) {
            if (!getProducts().contains(product)) {
                getProducts().add(product);
            }
            product.setCategory(this);
        }
    }

    public void removeProduct(Product product) {
        if (product.getCategory() == this) {
            if (getProducts().contains(product)) {
                getProducts().remove(product);
            }
            product.setCategory(null);
        }
    }


}
