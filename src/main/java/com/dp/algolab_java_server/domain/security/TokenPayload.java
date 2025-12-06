package com.dp.algolab_java_server.domain.security;

import lombok.Data;
import java.util.Date;
import lombok.AllArgsConstructor;

import com.dp.algolab_java_server.domain.entities.User;

@Data
@AllArgsConstructor
public class TokenPayload {
  private User user;

  private Date expiration;
}
