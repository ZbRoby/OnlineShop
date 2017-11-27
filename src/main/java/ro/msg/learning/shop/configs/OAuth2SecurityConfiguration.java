package ro.msg.learning.shop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ro.msg.learning.shop.entities.Role;
import ro.msg.learning.shop.repositories.UserRepository;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Configuration
class OAuth2SecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private final UserRepository userRepository;


    @Autowired
    public OAuth2SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username ->
            userRepository.findOptionalByUsername(username)
                .map(a ->
                    new User(a.getUsername(), a.getPassword(), true, true, true, true,
                        AuthorityUtils.createAuthorityList(a.getRoles().stream().map(Role::getName).toArray(String[]::new)))
                )
                .orElseThrow(
                    () -> new UsernameNotFoundException("could not find the user '" + username + "'"));
    }

}
