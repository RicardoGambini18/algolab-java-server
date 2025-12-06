package com.dp.algolab_java_server.domain.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BusinessException {
  public InvalidCredentialsException(String message) {
    super(message, HttpStatus.UNAUTHORIZED);
  }
}
