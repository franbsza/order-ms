package com.digital.orderms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIDocument {
    @Bean
    public OpenAPI microserviceOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Order Mngmt API")
                        .description("The Order Mngmt API aims to provide a way to manage orders.")
                        .version("v1.0.0"));
    }
}
