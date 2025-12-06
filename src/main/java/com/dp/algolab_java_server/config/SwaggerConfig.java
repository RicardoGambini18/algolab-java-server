package com.dp.algolab_java_server.config;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import org.springframework.core.io.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springdoc.webmvc.ui.SwaggerWelcomeCommon;
import org.springdoc.webmvc.ui.SwaggerIndexTransformer;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springdoc.webmvc.ui.SwaggerIndexPageTransformer;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.properties.SwaggerUiOAuthProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.web.servlet.resource.TransformedResource;
import org.springframework.web.servlet.resource.ResourceTransformerChain;

@Configuration
public class SwaggerConfig {
  @Bean
  OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Algolab Java Server")
            .version("1.0")
            .description(
                "Algolab es un laboratorio interactivo de algoritmos y estructuras de datos que permite experimentar con distintas implementaciones, comparar su rendimiento y visualizar su comportamiento en tiempo real, incluyendo comparadores de algoritmos de búsqueda y ordenamiento. Esta es la versión Java del servidor."))
        .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
        .components(new Components()
            .addSecuritySchemes("Bearer Authentication", createSecurityScheme()));
  }

  private SecurityScheme createSecurityScheme() {
    return new SecurityScheme()
        .name("Bearer Authentication")
        .type(SecurityScheme.Type.HTTP)
        .scheme("bearer")
        .bearerFormat("JWT");
  }

  @Bean
  SwaggerIndexTransformer swaggerIndexTransformer(
      SwaggerUiConfigProperties swaggerUiConfigProperties,
      SwaggerUiOAuthProperties swaggerUiOAuthProperties,
      SwaggerWelcomeCommon swaggerWelcomeCommon,
      ObjectMapperProvider objectMapperProvider) {
    return new SwaggerIndexPageTransformer(swaggerUiConfigProperties, swaggerUiOAuthProperties,
        swaggerWelcomeCommon, objectMapperProvider) {
      @Override
      public Resource transform(HttpServletRequest httpServletRequest,
          Resource resource,
          ResourceTransformerChain resourceTransformerChain)
          throws IOException {
        if (resource.toString().contains("index.html")) {
          final InputStream inputStream = resource.getInputStream();
          final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

          try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String cssTag = "<link rel=\"stylesheet\" href=\"/swagger-custom.css\">";
            final String html = bufferedReader.lines().collect(Collectors.joining());
            final byte[] transformedContent = html.replace("</head>", cssTag + "</head>").getBytes();
            return new TransformedResource(resource, transformedContent);
          }
        }

        return super.transform(httpServletRequest, resource, resourceTransformerChain);
      }
    };
  }
}
