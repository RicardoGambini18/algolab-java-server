package com.dp.algolab_java_server.domain.algorithms;

import java.util.List;
import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.entities.Movie;
import com.dp.algolab_java_server.domain.dtos.DataStructureDefinition;
import com.dp.algolab_java_server.domain.dtos.SortAlgorithmDefinition;
import com.dp.algolab_java_server.domain.dtos.SearchAlgorithmDefinition;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@DesignPattern(name = "Abstract Factory", solvedProblem = "Define un contrato agnóstico para la creación de familias de algoritmos (Búsqueda y Ordenamiento). Permite al cliente solicitar una estrategia por su identificador sin acoplarse a las clases concretas de implementación.")
public interface AlgorithmProvider {
  SortAlgorithm<Movie, ?> getSortAlgorithm(String algorithmKey, String dataStructureKey);

  SearchAlgorithm<Movie, ?> getSearchAlgorithm(String algorithmKey, String dataStructureKey);

  List<DataStructureDefinition<SortAlgorithmDefinition>> getSortingAlgorithmsCatalog();

  List<DataStructureDefinition<SearchAlgorithmDefinition>> getSearchingAlgorithmsCatalog();
}
