package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
public class ErrorResponse {
  @Schema(description = "Mensaje de error", example = "Ha ocurrido un error al procesar la solicitud")
  private String error;
}
