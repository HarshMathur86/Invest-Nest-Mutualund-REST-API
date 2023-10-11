package com.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Allow all origins. Adjust as needed.
        config.addAllowedHeader("*"); // Allow all headers. You can specify specific headers here.
        config.addAllowedMethod("*"); // Allow all HTTP methods. You can specify specific methods here.
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}