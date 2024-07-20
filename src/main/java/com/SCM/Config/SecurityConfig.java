package com.SCM.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.SCM.Services.Impls.SecurityCustomUserDetailService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService securityCustomUserDetailService;

    /**
     * // FOR IN MEMORY
     * 
     * @Bean
     *       public UserDetailsService userDetailsService() {
     *       UserDetails user1 =
     *       User.withUsername("suhag").password("{noop}suhag").roles("ADMIN",
     *       "USER").build();
     *       UserDetails user2 =
     *       User.withUsername("papun").password("{noop}papun").roles("USER").build();
     *       UserDetails user3 =
     *       User.withUsername("happy").password("{noop}happy").roles("ADMIN").build();
     * 
     *       InMemoryUserDetailsManager inMemoryUserDetailsManager = new
     *       InMemoryUserDetailsManager(user1, user2, user3);
     *       return inMemoryUserDetailsManager;
     *       }
     */

    /**
     * Configures the authentication provider to use custom user details service and
     * bcrypt password encoder.
     * 
     * @return an instance of AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Configures HTTP security settings, including URL authorization, CSRF, CORS,
     * and disabling the default form login.
     * 
     * @param httpSecurity instance of HttpSecurity
     * @return SecurityFilterChain for the application
     * @throws Exception in case of configuration issues
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        
        httpSecurity
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/user/**").authenticated();
                    authorize.anyRequest().permitAll();
                })
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .cors(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin.disable());
        return httpSecurity.build();
        /**
         * httpSecurity.authorizeHttpRequests(authorize -> {
         * authorize.requestMatchers("/user/**").authenticated();
         * authorize.anyRequest().permitAll();
         * });
         * httpSecurity.csrf(csrf -> csrf.disable());
         * httpSecurity.cors(Customizer.withDefaults());
         * 
         * httpSecurity.formLogin(formLogin -> {
         * formLogin.loginPage("/login");
         * formLogin.loginProcessingUrl("/login");
         * formLogin.successForwardUrl("/user/dashboard");
         * formLogin.failureForwardUrl("/login?error=true");
         * formLogin.usernameParameter("email");
         * formLogin.passwordParameter("password");
         * 
         * // formLogin.failureHandler(new AuthenticationFailureHandler() {
         * // @Override
         * // public void onAuthenticationFailure(HttpServletRequest request,
         * // HttpServletResponse response,
         * // AuthenticationException exception) throws IOException, ServletException {
         * // // TODO Auto-generated method stub
         * // throw new UnsupportedOperationException("Unimplemented method
         * // 'onAuthenticationFailure'");
         * // }
         * // });
         * // formLogin.successHandler(new AuthenticationSuccessHandler() {
         * // @Override
         * // public void onAuthenticationSuccess(HttpServletRequest request,
         * // HttpServletResponse response,
         * // Authentication authentication) throws IOException, ServletException {
         * // // TODO Auto-generated method stub
         * // throw new UnsupportedOperationException("Unimplemented method
         * // 'onAuthenticationSuccess'");
         * // }
         * // });
         * });
         * return httpSecurity.build();
         */
    }

    /**
     * Configures the password encoder to use BCrypt.
     * 
     * @return an instance of PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the authentication manager.
     * 
     * @param authenticationConfiguration the authentication configuration
     * @return an instance of AuthenticationManager
     * @throws Exception in case of configuration issues
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configures CORS settings, allowing requests from specific origins.
     * 
     * @return an instance of CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:1234")); // Allow requests from this origin
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
