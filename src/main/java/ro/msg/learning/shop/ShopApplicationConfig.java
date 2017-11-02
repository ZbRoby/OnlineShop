package ro.msg.learning.shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import ro.msg.learning.shop.converter.CsvHttpMessageConverter;
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
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> csvHttpMessageConverter = new CsvHttpMessageConverter();
        return new HttpMessageConverters(csvHttpMessageConverter);
    }

    @Bean
    public QuantityStrategy getQuantityStrategy() {
        switch (quantityStrategyType) {
            case GREEDY:
                return new GreedyQS();
            case MOSTABUNDANT:
                return new MostAbundantQS();
            case SINGLELOCATION:
                return new SingleLocationQS();
            default:
                throw new IllegalArgumentException();
        }
    }
}
