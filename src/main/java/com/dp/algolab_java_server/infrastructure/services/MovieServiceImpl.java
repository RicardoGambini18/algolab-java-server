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
import com.dp.algolab_java_server.domain.repositories.MovieRepository;

@Service
@RequiredArgsConstructor
@SolidPrinciple(name = "Principio de Responsabilidad Única (SRP)", solvedProblem = "Encapsula la lógica de obtención de películas, gestionando las reglas de paginación y ordenamiento requeridas por los algoritmos en un solo lugar, separando esta complejidad de los controladores.")
public class MovieServiceImpl implements MovieService {
  private final MovieRepository movieRepository;
  private final AppProperties appProperties;

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
}
