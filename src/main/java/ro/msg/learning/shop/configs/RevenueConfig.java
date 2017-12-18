package ro.msg.learning.shop.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.ZoneId;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
@EnableScheduling
public class RevenueConfig {

    @Value("${onlineShop.util.zoneIdString:Europe/Athens}")
    private String zoneIdString;

    @Bean
    public ZoneId getZoneId() {
        return ZoneId.of(zoneIdString);
    }
}
