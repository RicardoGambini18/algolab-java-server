package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Definición de algoritmo de ordenamiento")
public class SortAlgorithmDefinition extends BaseAlgorithmDefinition {
}
