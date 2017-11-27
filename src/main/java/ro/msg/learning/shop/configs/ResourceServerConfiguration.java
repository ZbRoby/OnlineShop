package ro.msg.learning.shop.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/rest/seeProducts/**", "/rest/orderCreator/**").hasAuthority("CUSTOMER")
            .antMatchers("/rest/resupply/**", "/rest/stock/**").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and().exceptionHandling()
            .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
