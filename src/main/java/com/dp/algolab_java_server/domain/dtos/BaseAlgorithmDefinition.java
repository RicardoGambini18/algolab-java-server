package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;

@Data
@SuperBuilder
public class BaseAlgorithmDefinition {
  @Schema(description = "Identificador único del algoritmo", example = "bubble_sort")
  private String key;

  @Schema(description = "Nombre del algoritmo", example = "Ordenamiento de Burbuja")
  private String name;

  @Schema(description = "Descripción breve del funcionamiento", example = "Algoritmo que intercambia elementos adyacentes...")
  private String description;

  @JsonProperty("time_complexity")
  @Schema(description = "Complejidad temporal teórica (Big O)", example = "O(n^2)")
  private String timeComplexity;

  @JsonProperty("time_complexity_level")
  @Schema(description = "Nivel de complejidad temporal", example = "high", implementation = AlgorithmComplexityLevel.class)
  private AlgorithmComplexityLevel timeComplexityLevel;

  @JsonProperty("space_complexity")
  @Schema(description = "Complejidad espacial teórica (Big O)", example = "O(1)")
  private String spaceComplexity;

  @JsonProperty("space_complexity_level")
  @Schema(description = "Nivel de complejidad espacial", example = "low", implementation = AlgorithmComplexityLevel.class)
  private AlgorithmComplexityLevel spaceComplexityLevel;
}
