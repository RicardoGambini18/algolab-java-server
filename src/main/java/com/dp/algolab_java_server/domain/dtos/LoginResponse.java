package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
public class LoginResponse {
  @Schema(description = "JSON Web Token (JWT) que contiene la información del usuario autenticado.", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
  private String token;
}
