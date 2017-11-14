package ro.msg.learning.shop.configs;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import ro.msg.learning.shop.converters.CsvHttpMessageConverter;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.mixins.*;
import ro.msg.learning.shop.models.OrderInput;
import ro.msg.learning.shop.models.ShelfProduct;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
public class ConvertersConfig {

    @Bean
    public HttpMessageConverter customConverters() {
        return new CsvHttpMessageConverter();
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
