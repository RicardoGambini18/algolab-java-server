package com.dp.algolab_java_server.domain.algorithms.searching.queue;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Queue;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@DesignPattern(name = "Strategy", solvedProblem = "Busca un elemento en una estructura FIFO rotando sus elementos y restaurando el orden original al completar un ciclo.")
public class LinearSearchQueueAlgorithm<T> extends SearchAlgorithm<T, Queue<T>> {

  public LinearSearchQueueAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
    super(valueGetter, algorithmProfiler);
  }

  @Override
  protected T search(Queue<T> queue, Integer valueToFind) {
    T itemFound = null;
    int size = queue.size();

    for (int i = 0; i < size; i++) {
      algorithmProfiler.incrementIterations();

      T item = queue.dequeue();

      if (itemFound == null) {
        int val = valueGetter.getValue(item);

        algorithmProfiler.incrementOperations(1);

        if (val == valueToFind) {
          itemFound = item;
        }
      }

      queue.enqueue(item);
    }

    return itemFound;
  }

  @Override
  protected Queue<T> createDataStructure(List<T> data) {
    return new Queue<>(data);
  }

  @Override
  public String getKey() {
    return "linear_search";
  }

  @Override
  public String getName() {
    return "Búsqueda lineal por rotación (Linear search by rotation)";
  }

  @Override
  public String getDescription() {
    return "Busca un elemento procesando el frente (dequeue) y reinsertándolo inmediatamente al final (enqueue). Realiza una rotación completa para garantizar que la cola regrese a su estado original. Operación O(n) obligatoria por la naturaleza FIFO.";
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