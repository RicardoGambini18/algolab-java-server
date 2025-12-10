package com.dp.algolab_java_server.domain.services;

import java.util.List;

import com.dp.algolab_java_server.common.SolidPrinciple;
import com.dp.algolab_java_server.domain.entities.Movie;
import com.dp.algolab_java_server.domain.dtos.SortAlgorithmResult;
import com.dp.algolab_java_server.domain.dtos.SearchAlgorithmResult;

@SolidPrinciple(name = "Principio de Inversión de Dependencias (DIP)", solvedProblem = "Desacopla el controlador de la implementación concreta de la lógica de negocio de películas. Define los contratos estrictos para la obtención de datos que consumirán los algoritmos de ordenamiento y búsqueda.")
public interface MovieService {
  List<Movie> getAllMovies();

  List<Movie> getMoviesForSorting();

  SortAlgorithmResult<Movie> sortMovies(String algorithmKey, String dataStructureKey, boolean includeResult);

  SearchAlgorithmResult<Movie> searchMovie(String algorithmKey, String dataStructureKey, Integer movieId,
      boolean includeResult);
}
