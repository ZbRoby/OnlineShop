package ro.msg.learning.shop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    // This is required for password grants, which we specify below as one of the
    // {@literal authorizedGrantTypes()}.
    private final AuthenticationManagerBuilder authenticationManager;

    @Autowired
    public AuthorizationServerConfiguration(AuthenticationManagerBuilder authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
        throws Exception {
        endpoints.authenticationManager(
            authentication -> authenticationManager.getOrBuild().authenticate(authentication));
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("android")
            .authorizedGrantTypes("password", "authorization_code", "refresh_token")
            .scopes("write", "read")
            .secret("123456")
            .accessTokenValiditySeconds(300)
            .refreshTokenValiditySeconds(600);
    }


}
