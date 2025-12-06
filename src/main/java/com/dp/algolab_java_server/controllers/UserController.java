package com.dp.algolab_java_server.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dp.algolab_java_server.domain.entities.User;
import com.dp.algolab_java_server.domain.dtos.LoginRequest;
import com.dp.algolab_java_server.domain.dtos.LoginResponse;
import com.dp.algolab_java_server.domain.services.UserService;
import com.dp.algolab_java_server.domain.services.AuthService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final AuthService authService;

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }
}
