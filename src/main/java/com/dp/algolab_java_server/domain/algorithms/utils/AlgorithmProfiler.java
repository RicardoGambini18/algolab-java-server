package com.dp.algolab_java_server.domain.algorithms.utils;

import com.dp.algolab_java_server.common.SolidPrinciple;
import com.dp.algolab_java_server.domain.dtos.AlgorithmMetrics;

@SolidPrinciple(name = "Principio de Segregación de Interfaces (ISP)", solvedProblem = "Define un contrato específico y cohesivo para el perfilado, evitando que los clientes dependan de métodos de medición que no necesitan o de detalles de implementación.")
@SolidPrinciple(name = "Principio de Inversión de Dependencias (DIP)", solvedProblem = "Permite que los algoritmos del dominio dependan de una abstracción (esta interfaz) para medir su rendimiento, desacoplándose de los detalles de bajo nivel (System.nanoTime, GC) de la infraestructura.")
public interface AlgorithmProfiler {
  void start();

  void end();

  void incrementOperations(int count);

  void incrementIterations();

  AlgorithmMetrics getMetrics();
}
