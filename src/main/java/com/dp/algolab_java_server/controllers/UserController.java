package com.dp.algolab_java_server.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.domain.entities.User;
import com.dp.algolab_java_server.domain.services.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final Logger log;

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    try {
      List<User> users = userService.getAllUsers();
      return ResponseEntity.ok(users);
    } catch (Exception e) {
      log.error("Error al obtener usuarios: " + e.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }
}
