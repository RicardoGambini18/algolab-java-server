package com.dp.algolab_java_server.controllers;

import java.util.List;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dp.algolab_java_server.domain.entities.User;
import com.dp.algolab_java_server.domain.dtos.LoginRequest;
import com.dp.algolab_java_server.domain.dtos.LoginResponse;
import com.dp.algolab_java_server.domain.dtos.ErrorResponse;
import com.dp.algolab_java_server.domain.services.UserService;
import com.dp.algolab_java_server.domain.services.AuthService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones de gestión de usuarios y autenticación")
public class UserController {
  private final UserService userService;
  private final AuthService authService;

  @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista con todos los usuarios registrados.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de usuarios", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @Operation(summary = "Iniciar sesión", description = "Autentica un usuario y retorna un JSON Web Token (JWT). Este token debe incluirse en el header Authorization con el formato 'Bearer {token}' para acceder a endpoints protegidos.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
      @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
      @ApiResponse(responseCode = "401", description = "Credenciales inválidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
      @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }
}
