package com.dp.algolab_java_server.infrastructure.algorithms;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.ObjectProvider;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.entities.Movie;
import com.dp.algolab_java_server.domain.data_structures.*;
import com.dp.algolab_java_server.domain.algorithms.sorting.stack.*;
import com.dp.algolab_java_server.domain.algorithms.sorting.vector.*;
import com.dp.algolab_java_server.domain.algorithms.searching.stack.*;
import com.dp.algolab_java_server.domain.dtos.DataStructureDefinition;
import com.dp.algolab_java_server.domain.dtos.SortAlgorithmDefinition;
import com.dp.algolab_java_server.domain.algorithms.AlgorithmProvider;
import com.dp.algolab_java_server.domain.data_structures.DataStructure;
import com.dp.algolab_java_server.domain.algorithms.searching.vector.*;
import com.dp.algolab_java_server.domain.dtos.SearchAlgorithmDefinition;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;
import com.dp.algolab_java_server.domain.exceptions.ResourceNotFoundException;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@Component
@RequiredArgsConstructor
@DesignPattern(name = "Registry", solvedProblem = "Centraliza la configuración de algoritmos disponibles utilizando mapas internos. Esto permite asociar claves (strings) con sus constructores correspondientes de forma dinámica, eliminando la necesidad de lógica condicional compleja (if/else) para la instanciación.")
public class AlgorithmProviderImpl implements AlgorithmProvider {
  private final ObjectProvider<AlgorithmProfiler> algorithmProfilerFactory;
  private final Map<String, DataStructureDefinition<SortAlgorithmDefinition>> sortingCatalogRegistry = new LinkedHashMap<>();
  private final Map<String, DataStructureDefinition<SearchAlgorithmDefinition>> searchingCatalogRegistry = new LinkedHashMap<>();
  private final Map<String, Map<String, Function<AlgorithmProfiler, SortAlgorithm<Movie, ?>>>> sortingAlgorithmsRegistry = new HashMap<>();
  private final Map<String, Map<String, Function<AlgorithmProfiler, SearchAlgorithm<Movie, ?>>>> searchingAlgorithmsRegistry = new HashMap<>();

  @PostConstruct
  public void init() {
    // Stack
    Stack<?> stack = new Stack<>();
    registerDataStructure(stack);
    // Stack Sorting Algorithms
    registerSortingAlgorithm(stack.getKey(),
        algorithmProfiler -> new SortStackAlgorithm<>(Movie::getId, algorithmProfiler));
    // Stack Searching Algorithms
    registerSearchingAlgorithm(stack.getKey(),
        algorithmProfiler -> new LinearSearchStackAlgorithm<>(Movie::getId, algorithmProfiler));
    // Vector
    Vector<?> vector = new Vector<>();
    registerDataStructure(vector);
    // Vector Sorting Algorithms
    registerSortingAlgorithm(vector.getKey(),
        algorithmProfiler -> new BubbleSortVectorAlgorithm<>(Movie::getId, algorithmProfiler));
    registerSortingAlgorithm(vector.getKey(),
        algorithmProfiler -> new SelectionSortVectorAlgorithm<>(Movie::getId, algorithmProfiler));
    registerSortingAlgorithm(vector.getKey(),
        algorithmProfiler -> new InsertionSortVectorAlgorithm<>(Movie::getId, algorithmProfiler));
    registerSortingAlgorithm(vector.getKey(),
        algorithmProfiler -> new MergeSortVectorAlgorithm<>(Movie::getId, algorithmProfiler));
    registerSortingAlgorithm(vector.getKey(),
        algorithmProfiler -> new QuickSortVectorAlgorithm<>(Movie::getId, algorithmProfiler));
    // Vector Searching Algorithms
    registerSearchingAlgorithm(vector.getKey(),
        algorithmProfiler -> new LinearSearchVectorAlgorithm<>(Movie::getId, algorithmProfiler));
    registerSearchingAlgorithm(vector.getKey(),
        algorithmProfiler -> new BinarySearchVectorAlgorithm<>(Movie::getId, algorithmProfiler));
    registerSearchingAlgorithm(vector.getKey(),
        algorithmProfiler -> new JumpSearchVectorAlgorithm<>(Movie::getId, algorithmProfiler));
    registerSearchingAlgorithm(vector.getKey(),
        algorithmProfiler -> new ExponentialSearchVectorAlgorithm<>(Movie::getId, algorithmProfiler));
    registerSearchingAlgorithm(vector.getKey(),
        algorithmProfiler -> new InterpolationSearchVectorAlgorithm<>(Movie::getId, algorithmProfiler));
  }

  private void registerDataStructure(DataStructure<?> dataStructure) {
    sortingCatalogRegistry.put(dataStructure.getKey(), dataStructure.toDefinition());
    searchingCatalogRegistry.put(dataStructure.getKey(), dataStructure.toDefinition());
  }

  private void registerSortingAlgorithm(String dataStructureKey,
      Function<AlgorithmProfiler, SortAlgorithm<Movie, ?>> sortAlgorithmConstructor) {
    sortingAlgorithmsRegistry.computeIfAbsent(dataStructureKey, key -> new HashMap<>());

    SortAlgorithm<Movie, ?> sortAlgorithm = sortAlgorithmConstructor.apply(null);
    String sortAlgorithmKey = sortAlgorithm.getKey();

    sortingAlgorithmsRegistry.get(dataStructureKey).put(sortAlgorithmKey, sortAlgorithmConstructor);

    DataStructureDefinition<SortAlgorithmDefinition> dataStructureDefinition = sortingCatalogRegistry
        .get(dataStructureKey);
    if (dataStructureDefinition != null) {
      dataStructureDefinition.getAlgorithms().add(sortAlgorithm.toDefinition());
    }
  }

  private void registerSearchingAlgorithm(String dataStructureKey,
      Function<AlgorithmProfiler, SearchAlgorithm<Movie, ?>> searchAlgorithmConstructor) {
    searchingAlgorithmsRegistry.computeIfAbsent(dataStructureKey, key -> new HashMap<>());

    SearchAlgorithm<Movie, ?> searchAlgorithm = searchAlgorithmConstructor.apply(null);
    String searchAlgorithmKey = searchAlgorithm.getKey();

    searchingAlgorithmsRegistry.get(dataStructureKey).put(searchAlgorithmKey, searchAlgorithmConstructor);

    DataStructureDefinition<SearchAlgorithmDefinition> dataStructureDefinition = searchingCatalogRegistry
        .get(dataStructureKey);
    if (dataStructureDefinition != null) {
      dataStructureDefinition.getAlgorithms().add(searchAlgorithm.toDefinition());
    }
  }

  @Override
  public List<DataStructureDefinition<SortAlgorithmDefinition>> getSortingAlgorithmsCatalog() {
    return new ArrayList<>(sortingCatalogRegistry.values());
  }

  @Override
  public List<DataStructureDefinition<SearchAlgorithmDefinition>> getSearchingAlgorithmsCatalog() {
    return new ArrayList<>(searchingCatalogRegistry.values());
  }

  @Override
  public SortAlgorithm<Movie, ?> getSortAlgorithm(String algorithmKey, String dataStructureKey) {
    var dataStructureSortingAlgorithms = sortingAlgorithmsRegistry.get(dataStructureKey);
    if (dataStructureSortingAlgorithms == null) {
      throw new ResourceNotFoundException(
          "La estructura de datos '" + dataStructureKey + "' no está soportada o no existe.");
    }

    var sortAlgorithmConstructor = dataStructureSortingAlgorithms.get(algorithmKey);
    if (sortAlgorithmConstructor == null) {
      throw new ResourceNotFoundException("El algoritmo de ordenamiento '" + algorithmKey
          + "' no encontrado para la estructura '" + dataStructureKey + "'.");
    }

    return sortAlgorithmConstructor.apply(algorithmProfilerFactory.getObject());
  }

  @Override
  public SearchAlgorithm<Movie, ?> getSearchAlgorithm(String algorithmKey, String dataStructureKey) {
    var dataStructureSearchingAlgorithms = searchingAlgorithmsRegistry.get(dataStructureKey);
    if (dataStructureSearchingAlgorithms == null) {
      throw new ResourceNotFoundException(
          "La estructura de datos '" + dataStructureKey + "' no está soportada o no existe.");
    }

    var searchAlgorithmConstructor = dataStructureSearchingAlgorithms.get(algorithmKey);
    if (searchAlgorithmConstructor == null) {
      throw new ResourceNotFoundException("El algoritmo de búsqueda '" + algorithmKey
          + "' no encontrado para la estructura '" + dataStructureKey + "'.");
    }

    return searchAlgorithmConstructor.apply(algorithmProfilerFactory.getObject());
  }
}