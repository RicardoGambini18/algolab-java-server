package com.dp.algolab_java_server.config;

import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.domain.dtos.ErrorResponse;
import com.dp.algolab_java_server.domain.exceptions.BusinessException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private final Logger log = Logger.getInstance();

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
    String errorMessage = e.getBindingResult().getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.joining(", "));

    return ResponseEntity.badRequest().body(new ErrorResponse(errorMessage));
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
    return ResponseEntity
        .status(e.getStatus())
        .body(new ErrorResponse(e.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception e) {
    log.error("Error interno del servidor: " + e.getMessage());

    return ResponseEntity.internalServerError().body(new ErrorResponse("Error interno del servidor"));
  }
}
