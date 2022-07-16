package org.henry.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().httpBasic().disable()
                .cors().configurationSource(CorsConfigurationSource());
    }

    @Bean
    protected CorsConfigurationSource CorsConfigurationSource() {
        return request -> {
            CorsConfiguration corsConfig = new CorsConfiguration();

            corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
            corsConfig.setExposedHeaders(Collections.singletonList("Authorization"));
            corsConfig.setAllowedHeaders(Collections.singletonList("*"));
            corsConfig.setAllowedMethods(Collections.singletonList("*"));
            corsConfig.setAllowCredentials(true);
            corsConfig.setMaxAge(3600L);

            return corsConfig;
        };
    }
}