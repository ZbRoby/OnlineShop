package ro.msg.learning.shop;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import ro.msg.learning.shop.converter.CsvHttpMessageConverter;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.mixin.*;
import ro.msg.learning.shop.model.OrderInput;
import ro.msg.learning.shop.model.ShelfProduct;
import ro.msg.learning.shop.services.strategies.GreedyQS;
import ro.msg.learning.shop.services.strategies.MostAbundantQS;
import ro.msg.learning.shop.services.strategies.QuantityStrategy;
import ro.msg.learning.shop.services.strategies.SingleLocationQS;
import ro.msg.learning.shop.services.strategies.enums.QuantityStrategyType;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
public class ShopApplicationConfig {

    @Value("${onlineShop.strategies.quantityStrategyType}")
    private QuantityStrategyType quantityStrategyType;

    @Bean
    public HttpMessageConverter customConverters() {
        return new CsvHttpMessageConverter();
    }

    @Bean
    public QuantityStrategy getQuantityStrategy() {
        switch (quantityStrategyType) {
            case GREEDY:
                return new GreedyQS();
            case MOST_ABUNDANT:
                return new MostAbundantQS();
            case SINGLE_LOCATION:
                return new SingleLocationQS();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Bean
    public Module jsonModule() {
        SimpleModule module = new SimpleModule();
        module.setMixInAnnotation(Address.class, AddressMixin.class);
        module.setMixInAnnotation(Customer.class, CustomerMixin.class);
        module.setMixInAnnotation(Employee.class, EmployeeMixin.class);
        module.setMixInAnnotation(Location.class, LocationMixin.class);
        module.setMixInAnnotation(Order.class, OrderMixin.class);
        module.setMixInAnnotation(OrderDetails.class, OrderDetailsMixin.class);
        module.setMixInAnnotation(Product.class, ProductMixin.class);
        module.setMixInAnnotation(ProductCategory.class, ProductCategoryMixin.class);
        module.setMixInAnnotation(ProductDetails.class, ProductDetailsMixin.class);
        module.setMixInAnnotation(ProductsLocations.class, ProductsLocationsMixin.class);
        module.setMixInAnnotation(OrderInput.class, OrderInputMixin.class);
        module.setMixInAnnotation(ShelfProduct.class, ShelfProductMixin.class);
        return module;
    }
}
