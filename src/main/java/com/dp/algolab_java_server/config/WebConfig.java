package com.dp.algolab_java_server.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.dp.algolab_java_server.common.Logger;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
  private final AppProperties appProperties;
  private final Logger log = Logger.getInstance();
  private static final String API_PREFIX = "api/";
  private static final String FRONTEND_DIR = "frontend";
  private static final String INDEX_HTML_FILE_NAME = "index.html";

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!Files.exists(Paths.get(FRONTEND_DIR).resolve(INDEX_HTML_FILE_NAME))) {
      return;
    }

    log.info("Sirviendo archivos estáticos desde la carpeta: " + FRONTEND_DIR);

    registry.addResourceHandler("/**")
        .addResourceLocations("file:" + FRONTEND_DIR + "/")
        .resourceChain(true)
        .addResolver(new PathResourceResolver() {
          @Override
          protected Resource getResource(String resourcePath, Resource location) throws IOException {
            if (resourcePath == null || resourcePath.isBlank() || "/".equals(resourcePath)) {
              return location.createRelative(INDEX_HTML_FILE_NAME);
            }

            Resource requestedResource = location.createRelative(resourcePath);

            if (requestedResource.exists() && requestedResource.isReadable() && requestedResource.getFile().isFile()) {
              return requestedResource;
            }

            if (resourcePath.startsWith(API_PREFIX)) {
              return null;
            }

            return location.createRelative(INDEX_HTML_FILE_NAME);
          }
        });
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("forward:/" + INDEX_HTML_FILE_NAME);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
        .allowedOrigins(appProperties.getFrontendUrl())
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("Authorization", "Content-Type")
        .allowCredentials(true);
  }
}
