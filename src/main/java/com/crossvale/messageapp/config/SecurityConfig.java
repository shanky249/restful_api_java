package com.crossvale.messageapp.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOGGER = LogManager.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        LOGGER.info("Configuring security");

        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/jwt/**").permitAll()
                                .antMatchers("/message/**").permitAll()
                                .antMatchers("/api/richandmorty/**").permitAll()
                                .antMatchers("/swagger-ui.html", "/swagger-ui/**",
                                        "/swagger-resources/**", "/swagger-resources", "/v2/api-docs/**",
                                        "/proxy/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf().disable();

        return http.build();
    }
}
