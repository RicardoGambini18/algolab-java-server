package com.dp.algolab_java_server.domain.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dp.algolab_java_server.domain.entities.User;
import com.dp.algolab_java_server.common.DesignPattern;

@Repository
@DesignPattern(name = "Repository", solvedProblem = "Abstrae la complejidad del acceso a datos y las consultas SQL, proporcionando una interfaz tipo colección para gestionar la persistencia de usuarios sin acoplar la lógica de negocio a la tecnología de base de datos.")
public interface UserRepository extends JpaRepository<User, Integer> {

}
