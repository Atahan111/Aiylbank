package com.example.demo.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    private static final String API_KEY = "Bearer Token";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(API_KEY, apiKeySecuritySchema()))
                .info(new Info().title("Aiyl-bank"))
                .security(Collections.singletonList(new SecurityRequirement().addList(API_KEY)));
    }

    public io.swagger.v3.oas.models.security.SecurityScheme apiKeySecuritySchema() {
        return new io.swagger.v3.oas.models.security.SecurityScheme()
                .name("Bearer Token")
                .description("Please put the token")
                .in(io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer");
    }
}
