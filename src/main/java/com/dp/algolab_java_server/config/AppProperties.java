package com.dp.algolab_java_server.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
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
