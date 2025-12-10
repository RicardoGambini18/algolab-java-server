package com.dp.algolab_java_server.domain.data_structures;

import java.util.List;
import java.util.Collections;
import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.dtos.BaseAlgorithmDefinition;
import com.dp.algolab_java_server.domain.dtos.DataStructureDefinition;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Template Method", solvedProblem = "Define el esqueleto de inicialización en el constructor, delegando la construcción interna a las subclases.")
public abstract class DataStructure<T> {
  protected AlgorithmProfiler algorithmProfiler;

  public DataStructure() {
    this(Collections.emptyList());
  }

  public DataStructure(List<T> data) {
    buildFromList(data != null ? data : Collections.emptyList());
  }

  public void setAlgorithmProfiler(AlgorithmProfiler algorithmProfiler) {
    this.algorithmProfiler = algorithmProfiler;
  }

  protected abstract void buildFromList(List<T> data);

  public abstract List<T> toList();

  public abstract String getKey();

  public abstract String getName();

  public abstract String getDescription();

  public <A extends BaseAlgorithmDefinition> DataStructureDefinition<A> toDefinition() {
    return DataStructureDefinition.<A>builder()
        .key(getKey())
        .name(getName())
        .description(getDescription())
        .build();
  }
}
