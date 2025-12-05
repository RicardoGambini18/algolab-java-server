package com.dp.algolab_java_server.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.dp.algolab_java_server.common.SolidPrinciple;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
@SolidPrinciple(name = "Principio de Responsabilidad Única (SRP)", solvedProblem = "Evita el antipatrón 'Magic Numbers/Strings' y la dispersión de la configuración, centralizando todas las propiedades en una única estructura fuertemente tipada.")
public class AppProperties {
  private String frontendUrl;
  private int moviesSortLimit;
  private int warmupIterations;

  private final Security security = new Security();

  @Data
  public static class Security {
    private String secret;
    private String password;
    private long jwtExpirationMs;
  }
}
