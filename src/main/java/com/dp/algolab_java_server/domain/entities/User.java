package com.dp.algolab_java_server.domain.entities;

import lombok.Data;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Identificador único del usuario", example = "1")
  private Integer id;

  @Column(nullable = false)
  @Schema(description = "Nombre del usuario", example = "Juan Pérez")
  private String name;

  @Column(nullable = false, unique = true)
  @Schema(description = "Email del usuario", example = "juan.perez@example.com")
  private String email;

  @Column
  @Schema(description = "Imagen del usuario", example = "https://example.com/avatar.png")
  private String image;
}
