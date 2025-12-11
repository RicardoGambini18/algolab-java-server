package com.dp.algolab_java_server.domain.algorithms.sorting.queue;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Queue;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Strategy", solvedProblem = "Adapta Merge Sort a estructuras secuenciales FIFO. Divide la cola en mitades y fusiona comparando los frentes.")
public class MergeSortQueueAlgorithm<T> extends SortAlgorithm<T, Queue<T>> {

  public MergeSortQueueAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
    super(valueGetter, algorithmProfiler);
  }

  @Override
  protected Queue<T> sort(Queue<T> queue) {
    return recursiveMergeSort(queue);
  }

  private Queue<T> recursiveMergeSort(Queue<T> queue) {
    algorithmProfiler.incrementIterations();

    int n = queue.size();

    if (n <= 1) {
      return queue;
    }

    Queue<T> left = new Queue<>(algorithmProfiler);
    Queue<T> right = new Queue<>(algorithmProfiler);
    int mid = n / 2;

    for (int i = 0; i < mid; i++) {
      algorithmProfiler.incrementIterations();

      left.enqueue(queue.dequeue());
    }

    while (!queue.isEmpty()) {
      algorithmProfiler.incrementIterations();

      right.enqueue(queue.dequeue());
    }

    Queue<T> leftSorted = recursiveMergeSort(left);
    Queue<T> rightSorted = recursiveMergeSort(right);

    return merge(leftSorted, rightSorted);
  }

  private Queue<T> merge(Queue<T> left, Queue<T> right) {
    Queue<T> merged = new Queue<>(algorithmProfiler);

    while (!left.isEmpty() && !right.isEmpty()) {
      algorithmProfiler.incrementIterations();

      T itemA = left.peek();
      T itemB = right.peek();
      int valA = valueGetter.getValue(itemA);
      int valB = valueGetter.getValue(itemB);

      algorithmProfiler.incrementOperations(1);

      if (valA <= valB) {
        merged.enqueue(left.dequeue());
      } else {
        merged.enqueue(right.dequeue());
      }
    }

    while (!left.isEmpty()) {
      algorithmProfiler.incrementIterations();

      merged.enqueue(left.dequeue());
    }

    while (!right.isEmpty()) {
      algorithmProfiler.incrementIterations();

      merged.enqueue(right.dequeue());
    }

    return merged;
  }

  @Override
  protected Queue<T> createDataStructure(List<T> data) {
    return new Queue<>(data);
  }

  @Override
  public String getKey() {
    return "merge_sort";
  }

  @Override
  public String getName() {
    return "Ordenamiento por fusión (Merge sort)";
  }

  @Override
  public String getDescription() {
    return "Adapta el algoritmo divide y vencerás a estructuras secuenciales. Divide la cola en mitades, ordena recursivamente y fusiona comparando los frentes. Es la opción más eficiente para estructuras enlazadas como las colas.";
  }

  @Override
  public String getTimeComplexity() {
    return "O(n\\log n)";
  }

  @Override
  public AlgorithmComplexityLevel getTimeComplexityLevel() {
    return AlgorithmComplexityLevel.LOW;
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