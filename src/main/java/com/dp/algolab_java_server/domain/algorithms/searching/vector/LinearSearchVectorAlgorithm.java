package com.dp.algolab_java_server.domain.algorithms.searching.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa la estrategia de búsqueda lineal (fuerza bruta) sobre la estructura Vector, recorriendo secuencialmente los elementos hasta encontrar el objetivo.")
public class LinearSearchVectorAlgorithm<T> extends SearchAlgorithm<T, Vector<T>> {
  public LinearSearchVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
    super(valueGetter, algorithmProfiler);
  }

  @Override
  protected T search(Vector<T> vector, Integer valueToFind) {
    int n = vector.length();

    for (int i = 0; i < n; i++) {
      algorithmProfiler.incrementIterations();

      T item = vector.at(i);
      int val = valueGetter.getValue(item);

      algorithmProfiler.incrementOperations(1);

      if (val == valueToFind) {
        return item;
      }
    }

    return null;
  }

  @Override
  protected Vector<T> createDataStructure(List<T> data) {
    return new Vector<>(data);
  }

  @Override
  public String getKey() {
    return "linear_search";
  }

  @Override
  public String getName() {
    return "Búsqueda lineal (Linear search)";
  }

  @Override
  public String getDescription() {
    return "Realiza un recorrido secuencial comparando cada elemento con el objetivo. Es el único método viable para datos desordenados. Su simplicidad lo hace útil para arreglos pequeños.";
  }

  @Override
  public boolean needsSort() {
    return false;
  }

  @Override
  public String getTimeComplexity() {
    return "O(n)";
  }

  @Override
  public AlgorithmComplexityLevel getTimeComplexityLevel() {
    return AlgorithmComplexityLevel.HIGH;
  }

  @Override
  public String getSpaceComplexity() {
    return "O(1)";
  }

  @Override
  public AlgorithmComplexityLevel getSpaceComplexityLevel() {
    return AlgorithmComplexityLevel.LOW;
  }
}
