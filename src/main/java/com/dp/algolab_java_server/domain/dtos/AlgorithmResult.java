package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.common.SolidPrinciple;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;

@Data
@SuperBuilder
@NoArgsConstructor
@DesignPattern(name = "Builder", solvedProblem = "Facilita la creación de objetos inmutables complejos en una jerarquía de herencia, permitiendo que las clases hijas (Sort/Search) inicialicen los campos de la clase padre (Métricas/Metadata) de manera fluida.")
@SolidPrinciple(name = "Principio de Abierto/Cerrado (OCP)", solvedProblem = "La clase base define la estructura común de un resultado algorítmico, abierta a extensión para resultados de nuevos tipos de algoritmos (algoritmos de voraces, algoritmos matemáticos, etc.) sin modificar la estructura base.")
public abstract class AlgorithmResult {
  @JsonProperty("data_structure")
  @Schema(description = "Nombre de la estructura de datos utilizada", example = "Vector")
  private String dataStructure;

  @Schema(description = "Nombre del algoritmo ejecutado", example = "Bubble Sort")
  private String algorithm;

  @JsonProperty("item_count")
  @Schema(description = "Cantidad de elementos procesados", example = "1000")
  private int itemCount;

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

  @Schema(description = "Métricas de rendimiento de la ejecución")
  private AlgorithmMetrics metrics;
}
