package ro.msg.learning.shop.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.entities.ProductDetails;
import ro.msg.learning.shop.entities.ProductsLocations;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface ProductMixin {

    @JsonProperty("Id")
    long getId();

    @JsonProperty("Name")
    String getName();

    @JsonProperty("SupplierName")
    String getSupplierName();

    @JsonProperty("Description")
    String getDescription();

    @JsonProperty("CurrencyCode")
    String getCurrencyCode();

    @JsonProperty("Price")
    double getPrice();

    @JsonProperty("ProductDetails")
    ProductDetails getProductDetails();

    @JsonProperty("Category")
    ProductCategory getCategory();

    @JsonIgnore
    List<ProductsLocations> getProductsLocations();
}
