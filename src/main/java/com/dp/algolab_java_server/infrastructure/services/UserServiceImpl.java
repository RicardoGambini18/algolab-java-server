package com.dp.algolab_java_server.infrastructure.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dp.algolab_java_server.domain.entities.User;
import com.dp.algolab_java_server.common.SolidPrinciple;
import com.dp.algolab_java_server.domain.services.UserService;
import com.dp.algolab_java_server.domain.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@SolidPrinciple(name = "Principio de Responsabilidad Única (SRP)", solvedProblem = "Centraliza la lógica de negocio y coordinación de datos de los usuarios en una única clase, evitando mezclar reglas de negocio con la gestión de peticiones HTTP (Controlador) o consultas SQL directas.")
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public User getUserById(Integer id) {
    return userRepository.findById(id).orElse(null);
  }
}
