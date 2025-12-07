package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import java.util.List;
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
@DesignPattern(name = "Builder", solvedProblem = "Extiende el builder del padre para incluir la lista ordenada, permitiendo construir el objeto final completo en una sola cadena de llamadas.")
public class SortAlgorithmResult<T> extends AlgorithmResult {
  @JsonProperty("sorted_data")
  @Schema(description = "Lista de datos ordenados")
  private List<T> sortedData;
}
