package ro.msg.learning.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@SpringBootApplication
@ComponentScan(basePackages = "ro.msg.learning.shop")
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}
