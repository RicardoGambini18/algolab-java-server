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
    return "Estructura lineal LIFO (Last In, First Out). Implementada sobre la clase legacy 'java.util.Stack', la cual es sincronizada (Thread-Safe). Esto garantiza seguridad en concurrencia pero añade un overhead significativo de tiempo en cada operación debido al bloqueo de monitor, haciéndola más lenta que implementaciones no sincronizadas.";
  }
}
