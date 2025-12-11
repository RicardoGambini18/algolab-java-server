package com.dp.algolab_java_server.domain.algorithms.sorting.queue;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Queue;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Strategy", solvedProblem = "Ordena una Cola rotando todos los elementos para encontrar el mínimo en cada pasada y moverlo a una estructura ordenada.")
public class SelectionSortQueueAlgorithm<T> extends SortAlgorithm<T, Queue<T>> {

  public SelectionSortQueueAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
    super(valueGetter, algorithmProfiler);
  }

  @Override
  protected Queue<T> sort(Queue<T> inputQueue) {
    Queue<T> sortedQueue = new Queue<>(algorithmProfiler);
    int n = inputQueue.size();

    for (int i = 0; i < n; i++) {
      algorithmProfiler.incrementIterations();

      T minItem = null;
      int minValue = Integer.MAX_VALUE;
      int currentSize = inputQueue.size();

      for (int j = 0; j < currentSize; j++) {
        algorithmProfiler.incrementIterations();

        T item = inputQueue.dequeue();
        int itemValue = valueGetter.getValue(item);

        algorithmProfiler.incrementOperations(1);

        if (minItem == null) {
          minItem = item;
          minValue = itemValue;
        } else {
          algorithmProfiler.incrementOperations(1);

          if (itemValue < minValue) {
            inputQueue.enqueue(minItem);
            minItem = item;
            minValue = itemValue;
          } else {
            inputQueue.enqueue(item);
          }
        }
      }

      if (minItem != null) {
        sortedQueue.enqueue(minItem);
      }
    }

    return sortedQueue;
  }

  @Override
  protected Queue<T> createDataStructure(List<T> data) {
    return new Queue<>(data);
  }

  @Override
  public String getKey() {
    return "selection_sort";
  }

  @Override
  public String getName() {
    return "Ordenamiento por selección (Selection sort)";
  }

  @Override
  public String getDescription() {
    return "Ordena la cola rotando todos los elementos para encontrar el mínimo en cada pasada y moverlo a una estructura ordenada. Implementación iterativa que respeta el acceso FIFO sin usar acceso aleatorio.";
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
    return "O(n)";
  }

  @Override
  public AlgorithmComplexityLevel getSpaceComplexityLevel() {
    return AlgorithmComplexityLevel.HIGH;
  }
}