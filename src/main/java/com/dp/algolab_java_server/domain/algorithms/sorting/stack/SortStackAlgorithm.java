package com.dp.algolab_java_server.domain.algorithms.sorting.stack;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Stack;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Strategy", solvedProblem = "Ordena una Pila utilizando únicamente operaciones permitidas (push/pop/peek) y una pila auxiliar, simulando inserción ordenada.")
public class SortStackAlgorithm<T> extends SortAlgorithm<T, Stack<T>> {
  public SortStackAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
    super(valueGetter, algorithmProfiler);
  }

  @Override
  protected Stack<T> sort(Stack<T> inputStack) {
    Stack<T> sortedStack = new Stack<>(algorithmProfiler);

    while (!inputStack.isEmpty()) {
      algorithmProfiler.incrementIterations();

      T currentItem = inputStack.pop();
      int currentVal = valueGetter.getValue(currentItem);

      while (!sortedStack.isEmpty()) {
        algorithmProfiler.incrementIterations();

        T topSorted = sortedStack.peek();
        int topVal = valueGetter.getValue(topSorted);

        algorithmProfiler.incrementOperations(1);

        if (topVal > currentVal) {
          inputStack.push(sortedStack.pop());
        } else {
          break;
        }
      }

      sortedStack.push(currentItem);
    }

    return sortedStack;
  }

  @Override
  protected Stack<T> createDataStructure(List<T> data) {
    return new Stack<>(data);
  }

  @Override
  public String getKey() {
    return "sort_stack";
  }

  @Override
  public String getName() {
    return "Ordenamiento de pila (Sort stack)";
  }

  @Override
  public String getDescription() {
    return "Algoritmo iterativo que ordena una pila utilizando únicamente una pila auxiliar y operaciones push/pop/peek. Simula una inserción ordenada moviendo elementos entre las dos pilas. Respeta estrictamente la restricción LIFO.";
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
