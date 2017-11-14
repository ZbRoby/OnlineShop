package ro.msg.learning.shop.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.services.strategies.GreedyQS;
import ro.msg.learning.shop.services.strategies.MostAbundantQS;
import ro.msg.learning.shop.services.strategies.QuantityStrategy;
import ro.msg.learning.shop.services.strategies.SingleLocationQS;
import ro.msg.learning.shop.services.strategies.enums.QuantityStrategyType;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
public class StrategyConfig {

    @Value("${onlineShop.strategies.quantityStrategyType}")
    private QuantityStrategyType quantityStrategyType;

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
}
