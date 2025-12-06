package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class LoginRequest {
  @NotNull(message = "La propiedad 'user_id' es requerida")
  @JsonProperty("user_id")
  @Schema(description = "Identificador único del usuario", example = "1")
  private Integer userId;

  @NotNull(message = "La propiedad 'password' es requerida")
  @Schema(description = "Contraseña", example = "35772")
  private String password;
}
