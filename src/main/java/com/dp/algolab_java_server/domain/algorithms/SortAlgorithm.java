package com.dp.algolab_java_server.domain.algorithms;

import java.util.List;
import lombok.RequiredArgsConstructor;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.dtos.SortAlgorithmResult;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.MetricsManager;

@RequiredArgsConstructor
@DesignPattern(name = "Template Method", solvedProblem = "Define el esqueleto de ejecución y medición de métricas en la clase base, permitiendo intercambiar estrategias de ordenamiento concretas sin duplicar la lógica de infraestructura.")
@DesignPattern(name = "Strategy", solvedProblem = "Permite intercambiar diferentes estrategias de ordenamiento (Bubble, Merge, etc.) que heredan de esta clase base, cumpliendo un contrato común para el cliente.")
public abstract class SortAlgorithm<T> {
  protected final ValueGetter<T> valueGetter;
  protected final MetricsManager metricsManager;

  public SortAlgorithmResult<T> execute(List<T> data) {
    metricsManager.start();

    List<T> sortedData = sort(data);

    metricsManager.end();

    return SortAlgorithmResult.<T>builder()
        .algorithm(getName())
        .dataStructure(getDataStructureName())
        .itemCount(data.size())
        .sortedData(sortedData)
        .metrics(metricsManager.getMetrics())
        .timeComplexity(getTimeComplexity())
        .timeComplexityLevel(getTimeComplexityLevel())
        .spaceComplexity(getSpaceComplexity())
        .spaceComplexityLevel(getSpaceComplexityLevel())
        .build();
  }

  protected abstract List<T> sort(List<T> data);

  public abstract String getDataStructureName();

  public abstract String getName();

  public abstract String getTimeComplexity();

  public abstract AlgorithmComplexityLevel getTimeComplexityLevel();

  public abstract String getSpaceComplexity();

  public abstract AlgorithmComplexityLevel getSpaceComplexityLevel();
}
