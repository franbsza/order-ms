package com.digital.orderms.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    private final KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfiguration())
                .and()
                .csrf().disable()
                .authorizeRequests(r -> r.antMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html")
                                .permitAll())
                .authorizeRequests(authz -> authz
                                .antMatchers("/api/**")
                                .authenticated())
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(keycloakJwtAuthenticationConverter);
    }

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.applyPermitDefaultValues();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedMethod("GET");
        corsConfig.addAllowedMethod("PATCH");
        corsConfig.addAllowedMethod("PUT");
        corsConfig.addAllowedMethod("POST");
        corsConfig.addAllowedMethod("OPTIONS");
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type",
                "Content-Type", "Referer", "Accept", "Access-Control-Allow-Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"));
        corsConfig.setExposedHeaders(Arrays.asList("X-Get-Header"));
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
}