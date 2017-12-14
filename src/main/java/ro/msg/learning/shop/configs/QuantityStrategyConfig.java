package ro.msg.learning.shop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repositories.CustomerRepository;
import ro.msg.learning.shop.services.strategies.*;
import ro.msg.learning.shop.services.strategies.enums.QuantityStrategyType;
import ro.msg.learning.shop.utils.DistanceCalculator;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
public class QuantityStrategyConfig {


    private final CustomerRepository customerRepository;
    private final DistanceCalculator distanceCalculator;

    @Value("${onlineShop.strategies.quantityStrategyType:GREEDY}")
    private QuantityStrategyType quantityStrategyType;

    @Autowired
    public QuantityStrategyConfig(CustomerRepository customerRepository, DistanceCalculator distanceCalculator) {
        this.customerRepository = customerRepository;
        this.distanceCalculator = distanceCalculator;
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
            case PROXIMITY:
                return new ProximityQS(customerRepository, distanceCalculator);
            default:
                throw new IllegalArgumentException();
        }
    }


}
