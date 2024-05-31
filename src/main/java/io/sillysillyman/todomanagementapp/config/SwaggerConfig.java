package io.sillysillyman.todomanagementapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Todo Management API").version("1.0")
            .description("API documentation for Todo Management Application"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("v1.0").pathsToMatch("/v1.0/**").build();
    }
}
