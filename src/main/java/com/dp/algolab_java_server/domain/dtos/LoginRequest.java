package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class LoginRequest {
  @NotNull(message = "La propiedad 'user_id' es requerida")
  @JsonProperty("user_id")
  private Integer userId;

  @NotNull(message = "La propiedad 'password' es requerida")
  private String password;
}
