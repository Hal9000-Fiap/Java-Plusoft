package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(contact = @Contact(name = "Diogo", email = "diogogallina20@gmail.com"),
                description = "Java Plusoft - API Whitelabel para empresas parceiras",
                title = "FIAP Plusoft - Whitelabel API",
                version = "1.0"
        ),
        servers = {
                @Server(description = "Dev Env", url = "http://localhost:8080"),
        },
        security = @SecurityRequirement(name = "bearerJWT")
)

@SecurityScheme(
        name="bearerJWT",
        description = "Autenticação básica JWT",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
