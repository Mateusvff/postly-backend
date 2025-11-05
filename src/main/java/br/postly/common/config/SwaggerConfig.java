package br.postly.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Postly: API",
                version = "1.0.0",
                contact = @Contact(name = "Mateus Franco", email = "mateusfrancovinicius@gmail.com"),
                description = "API Documentation"
        )
)
@Configuration
public class SwaggerConfig {
}