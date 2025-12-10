package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Definición de algoritmo de búsqueda")
public class SearchAlgorithmDefinition extends BaseAlgorithmDefinition {
  @JsonProperty("needs_sort")
  @Schema(description = "Indica si el algoritmo requiere datos ordenados previamente")
  private boolean needsSort;
}
