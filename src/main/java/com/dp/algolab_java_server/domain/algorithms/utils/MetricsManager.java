package com.dp.algolab_java_server.domain.algorithms.utils;

import com.dp.algolab_java_server.common.SolidPrinciple;
import com.dp.algolab_java_server.domain.dtos.AlgorithmMetrics;

@SolidPrinciple(name = "Principio de Inversión de Dependencias (DIP)", solvedProblem = "Permite que los algoritmos del dominio dependan de una abstracción para medir su rendimiento, sin conocer los detalles de bajo nivel (System.nanoTime, GC) que residen en la infraestructura.")
public interface MetricsManager {
  void start();

  void end();

  void incrementOperations(int count);

  void incrementIterations();

  AlgorithmMetrics getMetrics();
}
