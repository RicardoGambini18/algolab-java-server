package com.dp.algolab_java_server.infrastructure.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.dp.algolab_java_server.config.AppProperties;
import com.dp.algolab_java_server.domain.entities.Movie;
import com.dp.algolab_java_server.common.SolidPrinciple;
import com.dp.algolab_java_server.domain.services.MovieService;
import com.dp.algolab_java_server.domain.dtos.SortAlgorithmResult;
import com.dp.algolab_java_server.domain.dtos.SearchAlgorithmResult;
import com.dp.algolab_java_server.domain.algorithms.AlgorithmProvider;
import com.dp.algolab_java_server.domain.repositories.MovieRepository;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@Service
@RequiredArgsConstructor
@SolidPrinciple(name = "Principio de Responsabilidad Única (SRP)", solvedProblem = "Centraliza toda la lógica de dominio relacionada con las películas: desde la obtención de datos y paginación, hasta la orquestación de los algoritmos de búsqueda y ordenamiento, actuando como fachada única para el controlador.")
public class MovieServiceImpl implements MovieService {
  private final AppProperties appProperties;
  private final MovieRepository movieRepository;
  private final AlgorithmProvider algorithmProvider;

  @Override
  @Transactional(readOnly = true)
  public List<Movie> getAllMovies() {
    return movieRepository.findAllByOrderByIdAsc();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Movie> getMoviesForSorting() {
    int limit = appProperties.getMoviesSortLimit();
    Pageable pageable = PageRequest.of(0, limit);

    return movieRepository.findAllByOrderByTitleAsc(pageable);
  }

  @Override
  @Transactional(readOnly = true)
  public SortAlgorithmResult<Movie> sortMovies(String algorithmKey, String dataStructureKey, boolean includeResult) {
    List<Movie> movies = getMoviesForSorting();
    SortAlgorithm<Movie, ?> sortAlgorithm = algorithmProvider.getSortAlgorithm(algorithmKey, dataStructureKey);

    return sortAlgorithm.execute(movies, includeResult);
  }

  @Override
  @Transactional(readOnly = true)
  public SearchAlgorithmResult<Movie> searchMovie(String algorithmKey, String dataStructureKey, Integer movieId,
      boolean includeResult) {
    List<Movie> movies = getAllMovies();
    SearchAlgorithm<Movie, ?> searchAlgorithm = algorithmProvider.getSearchAlgorithm(algorithmKey, dataStructureKey);

    return searchAlgorithm.execute(movies, movieId, includeResult);
  }
}
