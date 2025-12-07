package com.dp.algolab_java_server.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AlgorithmComplexityLevel {
  LOW("low"),
  MEDIUM("medium"),
  HIGH("high");

  private final String value;

  AlgorithmComplexityLevel(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
