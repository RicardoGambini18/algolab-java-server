package com.dp.algolab_java_server.infrastructure.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.dp.algolab_java_server.config.AppProperties;
import com.dp.algolab_java_server.domain.entities.User;
import com.dp.algolab_java_server.common.SolidPrinciple;
import com.dp.algolab_java_server.domain.dtos.LoginRequest;
import com.dp.algolab_java_server.domain.dtos.LoginResponse;
import com.dp.algolab_java_server.domain.services.AuthService;
import com.dp.algolab_java_server.domain.security.TokenProvider;
import com.dp.algolab_java_server.domain.repositories.UserRepository;
import com.dp.algolab_java_server.domain.exceptions.InvalidCredentialsException;

@Service
@RequiredArgsConstructor
@SolidPrinciple(name = "Principio de Responsabilidad Única (SRP)", solvedProblem = "Encapsula exclusivamente la lógica de orquestación del proceso de login (búsqueda de usuario, verificación de credenciales y generación de tokens), separándola de la gestión de usuarios (UserService) y de los detalles HTTP.")
public class AuthServiceImpl implements AuthService {
  private final AppProperties appProperties;
  private final TokenProvider tokenProvider;
  private final UserRepository userRepository;

  @Override
  public LoginResponse login(LoginRequest request) {
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new InvalidCredentialsException("Credenciales inválidas"));

    String configPassword = appProperties.getSecurity().getPassword();
    if (!configPassword.equals(request.getPassword())) {
      throw new InvalidCredentialsException("Credenciales inválidas");
    }

    String token = tokenProvider.generateToken(user);

    return new LoginResponse(token);
  }
}
