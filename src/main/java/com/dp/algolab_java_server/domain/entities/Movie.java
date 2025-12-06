package com.dp.algolab_java_server.domain.entities;

import lombok.Data;
import java.time.LocalDate;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.dp.algolab_java_server.domain.enums.MovieStatus;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Identificador único de la película", example = "1")
  private Integer id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Schema(description = "Estado de la película", example = "RELEASED", implementation = MovieStatus.class)
  private MovieStatus status;

  @Column
  @Schema(description = "Presupuesto de la película", example = "30000000")
  private Long budget;

  @Column
  @Schema(description = "Recaudación de la película", example = "50000000")
  private Long revenue;

  @Column
  @Schema(description = "Página web de la película", example = "https://movie-homepage.com")
  private String homepage;

  @Column(name = "tmdb_id", nullable = false, unique = true)
  @JsonProperty("tmdb_id")
  @Schema(description = "Identificador único de la película en TMDB", example = "100")
  private Integer tmdbId;

  @Column(name = "imdb_id", nullable = false, unique = true)
  @JsonProperty("imdb_id")
  @Schema(description = "Identificador único de la película en IMDB", example = "110000")
  private Integer imdbId;

  @Column(nullable = false)
  @Schema(description = "Título de la película en inglés", example = "An Amazing Movie")
  private String title;

  @Column(name = "title_es")
  @JsonProperty("title_es")
  @Schema(description = "Título de la película en español", example = "Una Película Increíble")
  private String titleEs;

  @Column(nullable = false, columnDefinition = "TEXT")
  @Schema(description = "Resumen de la película en inglés", example = "This is the summary of the movie.")
  private String overview;

  @Column(name = "overview_es", columnDefinition = "TEXT")
  @JsonProperty("overview_es")
  @Schema(description = "Resumen de la película en español", example = "Este es el resumen de la película.")
  private String overviewEs;

  @Column(name = "poster_path")
  @JsonProperty("poster_path")
  @Schema(description = "Ruta relativa de la imagen de la película en TMDB", example = "/rhIRbceoE9lR4veEXuwCC2wARtG.jpg")
  private String posterPath;

  @Column(name = "release_date", nullable = false)
  @JsonProperty("release_date")
  @Schema(description = "Fecha de estreno de la película", example = "1999-01-01")
  private LocalDate releaseDate;

  @Column
  @Schema(description = "Slogan de la película en inglés", example = "This is the tagline of the movie.")
  private String tagline;

  @Column(name = "tagline_es")
  @JsonProperty("tagline_es")
  @Schema(description = "Slogan de la película en español", example = "Este es el slogan de la película.")
  private String taglineEs;

  @Column(name = "vote_average", nullable = false)
  @JsonProperty("vote_average")
  @Schema(description = "Puntuación promedio de la película", example = "7.5")
  private Double voteAverage;

  @Column(name = "vote_count", nullable = false)
  @JsonProperty("vote_count")
  @Schema(description = "Cantidad de votos de la película", example = "1000")
  private Integer voteCount;

  @Column(name = "original_language_id", nullable = false)
  @JsonProperty("original_language_id")
  @Schema(description = "Identificador único del idioma original de la película", example = "12")
  private Integer originalLanguageId;

  @Column(name = "collection_id")
  @JsonProperty("collection_id")
  @Schema(description = "Identificador único de la colección de la película", example = "1100")
  private Integer collectionId;
}
