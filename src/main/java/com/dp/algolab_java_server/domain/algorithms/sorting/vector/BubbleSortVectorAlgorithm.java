package com.dp.algolab_java_server.domain.algorithms.sorting.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa la lógica concreta del algoritmo Bubble Sort optimizado, definiendo tanto el comportamiento de ordenamiento como la estructura de datos requerida (Vector).")
public class BubbleSortVectorAlgorithm<T> extends SortAlgorithm<T, Vector<T>> {
  public BubbleSortVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
    super(valueGetter, algorithmProfiler);
  }

  @Override
  protected Vector<T> sort(Vector<T> vector) {
    int n = vector.length();

    for (int i = 0; i < n - 1; i++) {
      algorithmProfiler.incrementIterations();

      boolean swapped = false;

      for (int j = 0; j < n - i - 1; j++) {
        algorithmProfiler.incrementIterations();

        T itemA = vector.at(j);
        T itemB = vector.at(j + 1);

        int valA = valueGetter.getValue(itemA);
        int valB = valueGetter.getValue(itemB);

        algorithmProfiler.incrementOperations(1);

        if (valA > valB) {
          vector.swap(j, j + 1);
          swapped = true;
        }
      }

      if (!swapped)
        break;
    }

    return vector;
  }

  @Override
  protected Vector<T> createDataStructure(List<T> data) {
    return new Vector<>(data);
  }

  @Override
  public String getKey() {
    return "bubble_sort";
  }

  @Override
  public String getName() {
    return "Ordenamiento de burbuja (Bubble sort)";
  }

  @Override
  public String getDescription() {
    return "Algoritmo basado en comparaciones adyacentes. En cada pasada recorre el arreglo e intercambia pares fuera de orden, haciendo que el elemento extremo “burbujee” hasta su posición final. Esta implementación optimizada se detiene si no hay intercambios en una pasada.";
  }

  @Override
  public String getTimeComplexity() {
    return "O(n^2)";
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
