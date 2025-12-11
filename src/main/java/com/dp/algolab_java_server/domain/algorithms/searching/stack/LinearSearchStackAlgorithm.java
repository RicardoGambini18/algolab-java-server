package com.dp.algolab_java_server.domain.algorithms.searching.stack;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Stack;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@DesignPattern(name = "Strategy", solvedProblem = "Busca en una estructura LIFO desapilando elementos secuencialmente y restaurándolos posteriormente para preservar el estado original.")
public class LinearSearchStackAlgorithm<T> extends SearchAlgorithm<T, Stack<T>> {
  public LinearSearchStackAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
    super(valueGetter, algorithmProfiler);
  }

  @Override
  protected T search(Stack<T> stack, Integer valueToFind) {
    Stack<T> auxStack = new Stack<>(algorithmProfiler);
    T itemFound = null;

    while (!stack.isEmpty()) {
      algorithmProfiler.incrementIterations();

      T item = stack.pop();
      auxStack.push(item);

      if (itemFound == null) {
        int val = valueGetter.getValue(item);

        algorithmProfiler.incrementOperations(1);

        if (val == valueToFind) {
          itemFound = item;
          break;
        }
      }
    }

    while (!auxStack.isEmpty()) {
      algorithmProfiler.incrementIterations();

      stack.push(auxStack.pop());
    }

    return itemFound;
  }

  @Override
  protected Stack<T> createDataStructure(List<T> data) {
    return new Stack<>(data);
  }

  @Override
  public String getKey() {
    return "linear_search";
  }

  @Override
  public String getName() {
    return "Búsqueda lineal con restauración (Linear search with restoration)";
  }

  @Override
  public String getDescription() {
    return "Busca un elemento desapilando secuencialmente (pop) y guardando los datos en una pila auxiliar. Al finalizar, restaura los elementos a la pila original para no perder información. Costosa pero necesaria dada la restricción LIFO.";
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
    return "O(n)";
  }

  @Override
  public AlgorithmComplexityLevel getSpaceComplexityLevel() {
    return AlgorithmComplexityLevel.HIGH;
  }
}
