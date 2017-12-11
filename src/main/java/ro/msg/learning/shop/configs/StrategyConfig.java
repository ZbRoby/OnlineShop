package ro.msg.learning.shop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.repositories.CustomerRepository;
import ro.msg.learning.shop.services.strategies.*;
import ro.msg.learning.shop.services.strategies.enums.QuantityStrategyType;
import ro.msg.learning.shop.utils.DistanceCalculator;
import ro.msg.learning.shop.utils.DistanceCalculatorType;
import ro.msg.learning.shop.utils.GoogleDistanceCalculator;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
public class StrategyConfig {

    private final CustomerRepository customerRepository;

    @Value("${onlineShop.utils.distanceCalculator:GOOGLE}")
    private DistanceCalculatorType distanceCalculatorType;

    @Value("${onlineShop.utils.nrOfOrigins:50}")
    private int nrOfOrigins;

    @Value("${onlineShop.strategies.quantityStrategyType:GREEDY}")
    private QuantityStrategyType quantityStrategyType;

    @Value("${onlineShop.confidential.distanceApiKey}")
    private String distanceApiKey;

    @Value("${onlineShop.confidential.proxyDomain:NONE}")
    private String proxyDomain;

    @Value("${onlineShop.confidential.proxyPort:-1}")
    private int proxyPort;

    @Autowired
    public StrategyConfig(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate temp;
        if (!"NONE".equals(proxyDomain) && proxyPort != -1) {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyDomain, proxyPort)));
            temp = new RestTemplate(requestFactory);
        } else {
            temp = new RestTemplate();
        }

        return temp;
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
                return new ProximityQS(customerRepository, getDistanceCalculator());
            default:
                throw new IllegalArgumentException();
        }
    }

    @Bean
    public DistanceCalculator getDistanceCalculator() {
        if (distanceCalculatorType == DistanceCalculatorType.GOOGLE) {
            return new GoogleDistanceCalculator(getRestTemplate(), distanceApiKey, nrOfOrigins);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
