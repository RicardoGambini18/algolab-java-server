package com.dp.algolab_java_server.domain.data_structures;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import com.dp.algolab_java_server.common.DesignPattern;

@DesignPattern(name = "Adapter", solvedProblem = "Adapta la colección estándar 'ArrayList' de Java a la interfaz específica requerida por los algoritmos, centralizando operaciones de acceso y manipulación.")
public class Vector<T> extends DataStructure<T> {
  private List<T> items;

  public Vector() {
    super();
  }

  public Vector(List<T> data) {
    super(data);
  }

  @Override
  protected void buildFromList(List<T> data) {
    this.items = new ArrayList<>(data);
  }

  @Override
  public List<T> toList() {
    return new ArrayList<>(items);
  }

  public int length() {
    return items.size();
  }

  public void push(T element) {
    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(1);
    }
    items.add(element);
  }

  public T at(int index) {
    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(1);
    }
    return items.get(index);
  }

  public void update(int index, T element) {
    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(1);
    }
    items.set(index, element);
  }

  public void swap(int indexA, int indexB) {
    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(1);
    }
    Collections.swap(items, indexA, indexB);
  }

  public void extend(Vector<T> otherVector) {
    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(otherVector.length());
    }
    items.addAll(otherVector.toList());
  }

  public Vector<T> slice(int start, int end) {
    if (algorithmProfiler != null) {
      algorithmProfiler.incrementOperations(end - start);
    }

    List<T> subList = items.subList(start, end);
    Vector<T> newVector = new Vector<>(subList);

    newVector.setAlgorithmProfiler(this.algorithmProfiler);

    return newVector;
  }

  @Override
  public String getKey() {
    return "vector";
  }

  @Override
  public String getName() {
    return "Vector";
  }

  @Override
  public String getDescription() {
    return "Estructura de datos lineal donde los elementos se almacenan de forma contigua en memoria y se accede a ellos mediante un índice entero. Ofrece acceso aleatorio en tiempo constante y favorece recorridos secuenciales eficientes. Su tamaño suele ser fijo o costoso de cambiar, y las inserciones o eliminaciones en posiciones intermedias implican desplazamientos de elementos.";
  }
}
