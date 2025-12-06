package com.dp.algolab_java_server.domain.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class LoginResponse {
  private String token;
}
