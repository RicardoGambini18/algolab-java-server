package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import java.util.List;
import lombok.Builder;
import java.util.ArrayList;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@Schema(description = "Definición de estructura de datos y algoritmos soportados")
public class DataStructureDefinition {
  @Schema(description = "Identificador único de la estructura", example = "vector")
  private String key;

  @Schema(description = "Nombre de la estructura de datos", example = "Vector")
  private String name;

  @Schema(description = "Descripción técnica", example = "Estructura lineal de acceso contiguo...")
  private String description;

  @Schema(description = "Algoritmos de ordenamiento soportados")
  @JsonProperty("sorting_algorithms")
  @Builder.Default
  private List<SortAlgorithmDefinition> sortingAlgorithms = new ArrayList<>();

  @Schema(description = "Algoritmos de búsqueda soportados")
  @JsonProperty("searching_algorithms")
  @Builder.Default
  private List<SearchAlgorithmDefinition> searchingAlgorithms = new ArrayList<>();
}
