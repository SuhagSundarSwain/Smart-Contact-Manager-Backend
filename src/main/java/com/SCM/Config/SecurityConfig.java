package com.SCM.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("suhag").password("{noop}suhag").roles("ADMIN", "USER").build();
        UserDetails user2 = User.withUsername("papun").password("{noop}papun").roles("USER").build();
        UserDetails user3 = User.withUsername("happy").password("{noop}happy").roles("ADMIN").build();

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1, user2, user3);
        return inMemoryUserDetailsManager;
    }
}
