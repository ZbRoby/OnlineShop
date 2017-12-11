package ro.msg.learning.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@SpringBootApplication
@ComponentScan(basePackages = "ro.msg.learning.shop")
@PropertySource(value = "classpath:application.yaml")
@PropertySource(value = "classpath:applicationConfidential.properties")
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}
