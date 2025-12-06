package com.dp.algolab_java_server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dp.algolab_java_server.common.Logger;

@Configuration
public class AppConfig {
  @Bean
  Logger logger() {
    return Logger.getInstance();
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
