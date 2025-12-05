package com.dp.algolab_java_server.domain.services;

import java.util.List;

import com.dp.algolab_java_server.domain.entities.User;
import com.dp.algolab_java_server.common.SolidPrinciple;

@SolidPrinciple(name = "Principio de Inversión de Dependencias (DIP)", solvedProblem = "Desacopla el controlador de la implementación concreta de la lógica de negocio de usuarios. El controlador dependerá de esta abstracción (interfaz), permitiendo cambiar la implementación sin afectarlo.")
public interface UserService {
  List<User> getAllUsers();

  User getUserById(Integer id);
}
