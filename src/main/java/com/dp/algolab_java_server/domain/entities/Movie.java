package com.dp.algolab_java_server.domain.entities;

import lombok.Data;
import java.time.LocalDate;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String status;

  @Column
  private Long budget;

  @Column
  private Long revenue;

  @Column
  private String homepage;

  @Column(name = "tmdb_id", nullable = false, unique = true)
  private Integer tmdbId;

  @Column(name = "imdb_id", nullable = false, unique = true)
  private Integer imdbId;

  @Column(nullable = false)
  private String title;

  @Column(name = "title_es")
  private String titleEs;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String overview;

  @Column(name = "overview_es", columnDefinition = "TEXT")
  private String overviewEs;

  @Column(name = "poster_path")
  private String posterPath;

  @Column(name = "release_date", nullable = false)
  private LocalDate releaseDate;

  @Column
  private String tagline;

  @Column(name = "tagline_es")
  private String taglineEs;

  @Column(name = "vote_average", nullable = false)
  private Double voteAverage;

  @Column(name = "vote_count", nullable = false)
  private Integer voteCount;

  @Column(name = "original_language_id", nullable = false)
  private Integer originalLanguageId;

  @Column(name = "collection_id")
  private Integer collectionId;
}
