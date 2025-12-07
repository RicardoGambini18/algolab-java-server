package com.dp.algolab_java_server.domain.data_structures;

import java.util.List;
import com.dp.algolab_java_server.common.DesignPattern;

@DesignPattern(name = "Template Method", solvedProblem = "Define el esqueleto de inicialización en el constructor, delegando la construcción interna a las subclases.")
@DesignPattern(name = "Prototype", solvedProblem = "Permite la clonación de estructuras para que los algoritmos trabajen sobre copias sin afectar los datos originales.")
public abstract class DataStructure<T> implements Cloneable {
  public DataStructure(List<T> data) {
    buildFromList(data);
  }

  protected abstract void buildFromList(List<T> data);

  public abstract int size();

  public abstract boolean isEmpty();

  public abstract List<T> toList();

  @Override
  public abstract DataStructure<T> clone();
}
