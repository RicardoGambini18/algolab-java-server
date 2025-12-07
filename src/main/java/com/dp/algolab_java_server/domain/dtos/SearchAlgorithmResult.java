package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.dp.algolab_java_server.common.DesignPattern;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DesignPattern(name = "Builder", solvedProblem = "Extiende el builder del padre para incluir los detalles específicos de una búsqueda (ítem encontrado, posición), manteniendo la inmutabilidad y legibilidad.")
public class SearchAlgorithmResult<T> extends AlgorithmResult {
  @JsonProperty("needs_sort")
  @Schema(description = "Indica si el algoritmo requiere datos ordenados previamente")
  private boolean needsSort;

  @JsonProperty("item_found")
  @Schema(description = "El elemento encontrado")
  private T itemFound;

  @JsonProperty("item_found_position")
  @Schema(description = "Posición del elemento encontrado")
  private Integer itemFoundPosition;
}
