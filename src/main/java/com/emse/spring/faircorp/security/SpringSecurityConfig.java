package com.emse.spring.faircorp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SpringSecurityConfig {

    // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //  String currentPrincipalName = authentication.getName();


    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";

    @Bean
    public UserDetailsService userDetailsService() {
        // We create a password encoder
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("user").password(encoder.encode("myPassword")).roles(ROLE_USER).build()
        );
        manager.createUser(
                User.withUsername("admin").password(encoder.encode("adminpass")).roles(ROLE_ADMIN).build()
        );

        return manager;
    }

    @Bean
    @Order(1) // (1)
    public SecurityFilterChain filterChainAdmin(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()
                .antMatcher("/api/**") // (2)
                .authorizeRequests(authorize -> authorize.anyRequest().hasRole(ROLE_ADMIN)) // (3)
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    @Order(2)
    protected SecurityFilterChain filterChainH2Console(HttpSecurity http) throws Exception {
// found on https://stackoverflow.com/questions/53395200/h2-console-is-not-showing-in-browser
        return http
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .antMatcher("/console/**")
                .authorizeRequests(authorize -> authorize.anyRequest().hasRole(ROLE_ADMIN))
                .formLogin(withDefaults()) // (2)
                .httpBasic(withDefaults()) // (3)
                .build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .authorizeRequests(authorize -> authorize.anyRequest().authenticated()) // (1)
                .formLogin(withDefaults()) // (2)
                .httpBasic(withDefaults()) // (3)
                .build();
    }
}