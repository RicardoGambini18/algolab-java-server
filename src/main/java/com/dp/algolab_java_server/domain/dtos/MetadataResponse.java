package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
public class MetadataResponse {
  @Schema(description = "Nombre del curso", example = "Diseño de Patrones")
  private String course;

  @JsonProperty("password_hint")
  @Schema(description = "Pista para la contraseña", example = "Pista de ejemplo")
  private String passwordHint;
}
