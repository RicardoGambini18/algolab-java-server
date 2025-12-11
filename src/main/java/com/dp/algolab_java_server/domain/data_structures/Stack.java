package com.dp.algolab_java_server.domain.data_structures;

import java.util.List;
import java.util.ArrayList;
import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Adapter", solvedProblem = "Adapta la clase java.util.Stack para integrar el sistema de métricas (AlgorithmProfiler), permitiendo medir el costo de operaciones LIFO estándar.")
public class Stack<T> extends DataStructure<T> {
  private java.util.Stack<T> items;

  public Stack(List<T> data, AlgorithmProfiler algorithmProfiler) {
    super(data, algorithmProfiler);
  }

  public Stack(List<T> data) {
    super(data);
  }

  public Stack(AlgorithmProfiler algorithmProfiler) {
    super(algorithmProfiler);
  }

  public Stack() {
    super();
  }

  @Override
  protected void buildFromList(List<T> data) {
    this.items = new java.util.Stack<>();
    this.items.addAll(data);
  }

  @Override
  public List<T> toList() {
    return new ArrayList<>(items);
  }

  public void push(T element) {
    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(1);
    }
    items.push(element);
  }

  public T pop() {
    if (items.isEmpty()) {
      return null;
    }

    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(1);
    }

    return items.pop();
  }

  public T peek() {
    if (items.isEmpty()) {
      return null;
    }

    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(1);
    }

    return items.peek();
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  @Override
  public String getKey() {
    return "stack";
  }

  @Override
  public String getName() {
    return "Pila";
  }

  @Override
  public String getDescription() {
    return "Estructura de datos lineal que sigue el principio LIFO (Last In, First Out). El acceso está restringido estrictamente a la cima (tope). Es fundamental para la gestión de memoria (call stack), recursividad y evaluación de expresiones.";
  }
}
