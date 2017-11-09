package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@ToString(doNotUseGetters = true, exclude = "products")
@EqualsAndHashCode(doNotUseGetters = true, exclude = "products")
public class ProductCategory implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @OneToOne
    private ProductCategory mainCategory;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public ProductCategory() {
    }

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
