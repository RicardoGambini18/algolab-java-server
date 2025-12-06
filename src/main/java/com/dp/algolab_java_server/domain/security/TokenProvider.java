package com.dp.algolab_java_server.domain.security;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Claims;

import com.dp.algolab_java_server.domain.entities.User;
import com.dp.algolab_java_server.common.SolidPrinciple;

@SolidPrinciple(name = "Principio de Inversión de Dependencias (DIP)", solvedProblem = "Desacopla la lógica de negocio de la librería específica de generación de tokens (JJWT). El dominio define qué se necesita hacer con los tokens, y la infraestructura provee la implementación.")
public interface TokenProvider {
  String generateToken(User user);

  Jws<Claims> verifyToken(String token);
}
