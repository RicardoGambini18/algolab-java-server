package com.dp.algolab_java_server.domain.algorithms.sorting;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.dtos.SortAlgorithmResult;
import com.dp.algolab_java_server.domain.algorithms.BaseAlgorithm;
import com.dp.algolab_java_server.domain.dtos.SortAlgorithmDefinition;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.data_structures.DataStructure;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Template Method", solvedProblem = "Define el esqueleto de ejecución y medición de métricas en la clase base, permitiendo intercambiar estrategias de ordenamiento concretas sin duplicar la lógica de infraestructura.")
@DesignPattern(name = "Strategy", solvedProblem = "Permite intercambiar diferentes estrategias de ordenamiento (Bubble, Merge, etc.) que heredan de esta clase base, cumpliendo un contrato común para el cliente.")
public abstract class SortAlgorithm<T, S extends DataStructure<T>> extends BaseAlgorithm<T, S> {
  public SortAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
    super(valueGetter, algorithmProfiler);
  }

  public SortAlgorithmResult<T> execute(List<T> data, boolean includeResult) {
    S dataStructure = createDataStructure(data);
    dataStructure.setAlgorithmProfiler(algorithmProfiler);

    algorithmProfiler.start();
    S sortedDataStructure = sort(dataStructure);
    algorithmProfiler.end();

    var builder = SortAlgorithmResult.<T>builder()
        .algorithm(getName())
        .dataStructure(dataStructure.getName())
        .itemCount(data.size())
        .metrics(algorithmProfiler.getMetrics())
        .timeComplexity(getTimeComplexity())
        .timeComplexityLevel(getTimeComplexityLevel())
        .spaceComplexity(getSpaceComplexity())
        .spaceComplexityLevel(getSpaceComplexityLevel());

    if (includeResult) {
      builder.sortedData(sortedDataStructure.toList());
    }

    return builder.build();
  }

  protected abstract S sort(S dataStructure);

  @Override
  public SortAlgorithmDefinition toDefinition() {
    return SortAlgorithmDefinition.builder()
        .key(getKey())
        .name(getName())
        .description(getDescription())
        .timeComplexity(getTimeComplexity())
        .timeComplexityLevel(getTimeComplexityLevel())
        .spaceComplexity(getSpaceComplexity())
        .spaceComplexityLevel(getSpaceComplexityLevel())
        .build();
  }
}
