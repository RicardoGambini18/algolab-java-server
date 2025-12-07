package com.dp.algolab_java_server.domain.algorithms;

import java.util.List;
import lombok.RequiredArgsConstructor;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.dtos.SearchAlgorithmResult;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.MetricsManager;

@RequiredArgsConstructor
@DesignPattern(name = "Template Method", solvedProblem = "Estandariza el flujo de ejecución (inicio métricas -> búsqueda -> fin métricas -> construcción resultado) en la clase base, delegando la lógica específica de búsqueda a las subclases.")
@DesignPattern(name = "Strategy", solvedProblem = "Define una familia de algoritmos de búsqueda intercambiables bajo un contrato común, permitiendo que el cliente seleccione la estrategia concreta (Lineal, Binaria, etc.) sin acoplarse a su implementación.")
public abstract class SearchAlgorithm<T> {
  protected final ValueGetter<T> valueGetter;
  protected final MetricsManager metricsManager;

  public SearchAlgorithmResult<T> execute(List<T> data, Integer valueToFind) {
    metricsManager.start();

    T itemFound = search(data, valueToFind);

    metricsManager.end();

    Integer position = null;

    if (itemFound != null) {
      position = data.indexOf(itemFound);
      position = position == -1 ? null : position + 1;
    }

    return SearchAlgorithmResult.<T>builder()
        .algorithm(getName())
        .dataStructure(getDataStructureName())
        .itemCount(data.size())
        .itemFound(itemFound)
        .itemFoundPosition(position)
        .needsSort(needsSort())
        .metrics(metricsManager.getMetrics())
        .timeComplexity(getTimeComplexity())
        .timeComplexityLevel(getTimeComplexityLevel())
        .spaceComplexity(getSpaceComplexity())
        .spaceComplexityLevel(getSpaceComplexityLevel())
        .build();
  }

  protected abstract T search(List<T> data, Integer valueToFind);

  public abstract String getDataStructureName();

  public abstract String getName();

  public abstract boolean needsSort();

  public abstract String getTimeComplexity();

  public abstract AlgorithmComplexityLevel getTimeComplexityLevel();

  public abstract String getSpaceComplexity();

  public abstract AlgorithmComplexityLevel getSpaceComplexityLevel();
}
