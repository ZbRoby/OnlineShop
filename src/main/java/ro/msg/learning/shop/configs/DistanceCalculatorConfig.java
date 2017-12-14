package ro.msg.learning.shop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.utils.DistanceCalculator;
import ro.msg.learning.shop.utils.DistanceCalculatorType;
import ro.msg.learning.shop.utils.GoogleDistanceCalculator;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
public class DistanceCalculatorConfig {

    private final RestTemplate restTemplate;
    @Value("${onlineShop.utils.nrOfOrigins:50}")
    private int nrOfOrigins;
    @Value("${onlineShop.utils.distanceCalculator:GOOGLE}")
    private DistanceCalculatorType distanceCalculatorType;
    @Value("${onlineShop.utils.googleCalculatorUrl:https://maps.googleapis.com/maps/api/distancematrix}")
    private String url;
    @Value("${onlineShop.confidential.distanceApiKey}")
    private String distanceApiKey;

    @Autowired
    public DistanceCalculatorConfig(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean
    public DistanceCalculator getDistanceCalculator() {
        if (distanceCalculatorType == DistanceCalculatorType.GOOGLE) {
            return new GoogleDistanceCalculator(restTemplate, distanceApiKey, url, nrOfOrigins);
        } else {
            throw new IllegalArgumentException();
        }
    }


}
