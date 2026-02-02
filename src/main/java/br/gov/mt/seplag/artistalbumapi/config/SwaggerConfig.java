package br.gov.mt.seplag.artistalbumapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.tags.Tag;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Artistas e Álbuns")
                        .version("1.0.0")
                        .description("API REST para gerenciamento de artistas e álbuns")
                )
                .tags(List.of(
                        new Tag().name("Auth").description("Endpoints de autenticação"),
                        new Tag().name("Users").description("Endpoints de usuários"),
                        new Tag().name("Artists").description("Endpoints de artistas"),
                        new Tag().name("Albums").description("Endpoints de álbuns"),
                        new Tag().name("Regionals").description("Endpoints regionais")
                ))
                .schemaRequirement("jwt_auth", createSecurityScheme());
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name("jwt_auth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);
    }

}
