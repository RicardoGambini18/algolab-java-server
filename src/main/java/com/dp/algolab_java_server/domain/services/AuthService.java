package com.dp.algolab_java_server.domain.services;

import com.dp.algolab_java_server.common.SolidPrinciple;
import com.dp.algolab_java_server.domain.dtos.LoginRequest;
import com.dp.algolab_java_server.domain.dtos.LoginResponse;

@SolidPrinciple(name = "Principio de Inversión de Dependencias (DIP)", solvedProblem = "Define el contrato abstracto para la lógica de autenticación, permitiendo que los controladores deleguen el proceso de login sin conocer los detalles de implementación (validación de password, generación de tokens, etc.).")
public interface AuthService {
  LoginResponse login(LoginRequest request);
}
