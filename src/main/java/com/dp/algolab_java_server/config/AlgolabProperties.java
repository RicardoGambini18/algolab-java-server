package com.dp.algolab_java_server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "algolab")
public class AlgolabProperties {
  private int moviesSortLimit;
  private int warmupIterations;

  private final Security security = new Security();

  @Data
  public static class Security {
    private String password;
    private String secret;
    private long jwtExpirationMs;
  }
}
