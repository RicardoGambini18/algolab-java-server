package com.dp.algolab_java_server.domain.algorithms.searching;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.algorithms.BaseAlgorithm;
import com.dp.algolab_java_server.domain.dtos.SearchAlgorithmResult;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.data_structures.DataStructure;
import com.dp.algolab_java_server.domain.dtos.SearchAlgorithmDefinition;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Template Method", solvedProblem = "Estandariza el flujo de ejecución (inicio métricas -> búsqueda -> fin métricas -> construcción resultado) en la clase base, delegando la lógica específica de búsqueda a las subclases.")
@DesignPattern(name = "Strategy", solvedProblem = "Define una familia de algoritmos de búsqueda intercambiables bajo un contrato común, permitiendo que el cliente seleccione la estrategia concreta (Lineal, Binaria, etc.) sin acoplarse a su implementación.")
public abstract class SearchAlgorithm<T, S extends DataStructure<T>> extends BaseAlgorithm<T, S> {
  public SearchAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
    super(valueGetter, algorithmProfiler);
  }

  public SearchAlgorithmResult<T> execute(List<T> data, Integer valueToFind, boolean includeResult) {
    S dataStructure = createDataStructure(data);
    dataStructure.setAlgorithmProfiler(algorithmProfiler);

    algorithmProfiler.start();
    T itemFound = search(dataStructure, valueToFind);
    algorithmProfiler.end();

    Integer position = null;

    if (itemFound != null) {
      position = data.indexOf(itemFound);
      position = position == -1 ? null : position + 1;
    }

    var builder = SearchAlgorithmResult.<T>builder()
        .algorithm(getName())
        .dataStructure(dataStructure.getName())
        .itemCount(data.size())
        .itemFoundPosition(position)
        .needsSort(needsSort())
        .metrics(algorithmProfiler.getMetrics())
        .timeComplexity(getTimeComplexity())
        .timeComplexityLevel(getTimeComplexityLevel())
        .spaceComplexity(getSpaceComplexity())
        .spaceComplexityLevel(getSpaceComplexityLevel());

    if (includeResult) {
      builder.itemFound(itemFound);
    }

    return builder.build();
  }

  protected abstract T search(S dataStructure, Integer valueToFind);

  public abstract boolean needsSort();

  @Override
  public SearchAlgorithmDefinition toDefinition() {
    return SearchAlgorithmDefinition.builder()
        .key(getKey())
        .name(getName())
        .description(getDescription())
        .timeComplexity(getTimeComplexity())
        .timeComplexityLevel(getTimeComplexityLevel())
        .spaceComplexity(getSpaceComplexity())
        .spaceComplexityLevel(getSpaceComplexityLevel())
        .needsSort(needsSort())
        .build();
  }
}
