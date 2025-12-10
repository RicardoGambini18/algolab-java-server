package com.dp.algolab_java_server.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dp.algolab_java_server.domain.entities.Movie;
import com.dp.algolab_java_server.domain.dtos.ErrorResponse;
import com.dp.algolab_java_server.domain.services.MovieService;
import com.dp.algolab_java_server.domain.dtos.SortAlgorithmDefinition;
import com.dp.algolab_java_server.domain.dtos.DataStructureDefinition;
import com.dp.algolab_java_server.domain.algorithms.AlgorithmProvider;
import com.dp.algolab_java_server.domain.dtos.SearchAlgorithmDefinition;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(name = "Películas", description = "Operaciones de consulta y algoritmos sobre películas")
public class MovieController {
  private final MovieService movieService;
  private final AlgorithmProvider algorithmProvider;

  @Operation(summary = "Obtener todas las películas", description = "Retorna una lista con todas las películas de la base de datos, ordenadas por ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de películas", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Movie.class)))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping
  public ResponseEntity<List<Movie>> getMovies() {
    return ResponseEntity.ok(movieService.getAllMovies());
  }

  @Operation(summary = "Obtener catálogo de algoritmos de ordenamiento", description = "Retorna las estructuras de datos soportadas y sus algoritmos de ordenamiento.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Catálogo de algoritmos de ordenamiento", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DataStructureDefinition.class)))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/sort/data-structures")
  public ResponseEntity<List<DataStructureDefinition<SortAlgorithmDefinition>>> getSortingAlgorithmsCatalog() {
    return ResponseEntity.ok(algorithmProvider.getSortingAlgorithmsCatalog());
  }

  @Operation(summary = "Obtener catálogo de algoritmos de búsqueda", description = "Retorna las estructuras de datos soportadas y sus algoritmos de búsqueda.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Catálogo de algoritmos de búsqueda", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DataStructureDefinition.class)))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/search/data-structures")
  public ResponseEntity<List<DataStructureDefinition<SearchAlgorithmDefinition>>> getSearchingAlgorithmsCatalog() {
    return ResponseEntity.ok(algorithmProvider.getSearchingAlgorithmsCatalog());
  }
}
