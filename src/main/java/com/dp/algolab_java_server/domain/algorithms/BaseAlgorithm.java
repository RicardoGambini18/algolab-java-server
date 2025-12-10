package com.dp.algolab_java_server.domain.algorithms;

import java.util.List;
import lombok.RequiredArgsConstructor;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.dtos.BaseAlgorithmDefinition;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.data_structures.DataStructure;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@RequiredArgsConstructor
@DesignPattern(name = "Factory Method", solvedProblem = "Define la interfaz para crear un objeto (la estructura de datos), pero deja que las subclases decidan qué clase instanciar.")
public abstract class BaseAlgorithm<T, S extends DataStructure<T>> {
  protected final ValueGetter<T> valueGetter;

  protected final AlgorithmProfiler algorithmProfiler;

  protected abstract S createDataStructure(List<T> data);

  public abstract String getKey();

  public abstract String getName();

  public abstract String getDescription();

  public abstract String getTimeComplexity();

  public abstract AlgorithmComplexityLevel getTimeComplexityLevel();

  public abstract String getSpaceComplexity();

  public abstract AlgorithmComplexityLevel getSpaceComplexityLevel();

  public abstract BaseAlgorithmDefinition toDefinition();
}
