package com.dp.algolab_java_server.domain.repositories;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.entities.Movie;

@Repository
@DesignPattern(name = "Repository", solvedProblem = "Centraliza y encapsula la lógica de consultas específicas para películas, evitando la dispersión de sentencias SQL o HQL en la capa de servicio y promoviendo la reutilización de consultas.")
public interface MovieRepository extends JpaRepository<Movie, Integer> {
  List<Movie> findAllByOrderByIdAsc();

  List<Movie> findAllByOrderByTitleAsc(Pageable pageable);
}
