package com.dp.algolab_java_server.config;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.domain.exceptions.BusinessException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private final Logger log = Logger.getInstance();

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
    String errorMessage = e.getBindingResult().getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.joining(", "));

    return ResponseEntity.badRequest().body(Map.of("error", errorMessage));
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Map<String, String>> handleBusinessException(BusinessException e) {
    return ResponseEntity
        .status(e.getStatus())
        .body(Map.of("error", e.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleUnexpectedException(Exception e) {
    log.error("Error interno del servidor: " + e.getMessage());

    return ResponseEntity.internalServerError().body(Map.of("error", "Error interno del servidor"));
  }
}
