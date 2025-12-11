package com.dp.algolab_java_server.controllers;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dp.algolab_java_server.domain.dtos.MetadataResponse;
import com.dp.algolab_java_server.domain.dtos.ErrorResponse;

@RestController
@RequestMapping("/api/metadata")
@RequiredArgsConstructor
@Tag(name = "Metadata", description = "Información de metadatos de la aplicación")
public class MetadataController {

  @Operation(summary = "Obtener metadatos", description = "Retorna información de metadatos de la aplicación incluyendo el curso y la pista de contraseña.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Metadatos de la aplicación", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MetadataResponse.class))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping
  public ResponseEntity<MetadataResponse> getMetadata() {
    MetadataResponse response = new MetadataResponse(
        "Diseño de Patrones",
        "La contraseña es el número de sección del curso");
    return ResponseEntity.ok(response);
  }
}
