package com.dp.algolab_java_server.domain.data_structures;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Adapter", solvedProblem = "Adapta la interfaz java.util.Queue (usando LinkedList) para integrar el sistema de métricas, permitiendo medir el costo de operaciones FIFO estándar.")
public class Queue<T> extends DataStructure<T> {
  private java.util.Queue<T> items;

  public Queue() {
    super();
  }

  public Queue(List<T> data) {
    super(data);
  }

  public Queue(AlgorithmProfiler profiler) {
    super(profiler);
  }

  public Queue(List<T> data, AlgorithmProfiler profiler) {
    super(data, profiler);
  }

  @Override
  protected void buildFromList(List<T> data) {
    this.items = new LinkedList<>(data);
  }

  @Override
  public List<T> toList() {
    return new ArrayList<>(items);
  }

  public void enqueue(T element) {
    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(1);
    }

    items.add(element);
  }

  public T dequeue() {
    if (items.isEmpty()) {
      return null;
    }

    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(1);
    }

    return items.poll();
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

  public int size() {
    return items.size();
  }

  @Override
  public String getKey() {
    return "queue";
  }

  @Override
  public String getName() {
    return "Cola";
  }

  @Override
  public String getDescription() {
    return "Estructura lineal FIFO (First In, First Out). Implementada sobre 'java.util.LinkedList'. Aunque sus operaciones son O(1), consume más memoria que un Vector debido a que cada elemento se envuelve en un objeto 'Node' con punteros adicionales, generando overhead de asignación en cada inserción.";
  }
}