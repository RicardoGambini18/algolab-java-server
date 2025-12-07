package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dp.algolab_java_server.common.DesignPattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DesignPattern(name = "Builder", solvedProblem = "Permite la construcción fluida de objetos complejos (métricas con múltiples valores numéricos) sin necesidad de constructores con demasiados parámetros posicionales propensos a error.")
public class AlgorithmMetrics {
  @Schema(description = "Tiempo de ejecución en nanosegundos", example = "1500000")
  private long time;

  @Schema(description = "Memoria utilizada en bytes", example = "2048")
  private long memory;

  @Schema(description = "Cantidad de operaciones realizadas", example = "4500")
  private long operations;

  @Schema(description = "Cantidad de iteraciones realizadas en bucles", example = "500")
  private long iterations;
}
