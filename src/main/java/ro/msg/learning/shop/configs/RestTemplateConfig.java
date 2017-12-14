package ro.msg.learning.shop.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
public class RestTemplateConfig {

    @Value("${onlineShop.confidential.proxyDomain:NONE}")
    private String proxyDomain;
    @Value("${onlineShop.confidential.proxyPort:-1}")
    private int proxyPort;


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
}
