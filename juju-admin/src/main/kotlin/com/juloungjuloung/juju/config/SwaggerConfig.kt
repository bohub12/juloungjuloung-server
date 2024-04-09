package com.juloungjuloung.juju.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info =
    Info(
        title = "juju Admin API",
        description = "juju Admin API Restdocs",
        version = "v1"
    )
)
class SwaggerConfig {
    @Bean
    fun openApi(): OpenAPI {
        val schemeName = "Authorization"

        val securityRequirement =
            SecurityRequirement()
                .addList(schemeName)

        val components =
            Components()
                .addSecuritySchemes(
                    schemeName,
                    SecurityScheme()
                        .name(schemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .`in`(SecurityScheme.In.HEADER)
                        .scheme("Bearer")
                        .bearerFormat("JWT")
                )

        return OpenAPI()
            .addSecurityItem(securityRequirement)
            .components(components)
    }
}