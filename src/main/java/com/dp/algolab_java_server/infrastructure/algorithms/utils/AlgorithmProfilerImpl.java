package com.dp.algolab_java_server.infrastructure.algorithms.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import com.dp.algolab_java_server.config.AppProperties;
import com.dp.algolab_java_server.domain.dtos.AlgorithmMetrics;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AlgorithmProfilerImpl implements AlgorithmProfiler {
  private final int warmupIterations;

  @Getter
  private long operations = 0;
  @Getter
  private long iterations = 0;

  private long startTime;
  private long startMemory;
  private long endTime;
  private long endMemory;

  public AlgorithmProfilerImpl(AppProperties appProperties) {
    this.warmupIterations = appProperties.getWarmupIterations();
  }

  @Override
  public void incrementOperations(int count) {
    this.operations += count;
  }

  @Override
  public void incrementIterations() {
    this.iterations += 1;
  }

  @Override
  public void start() {
    reset();
    warmup();

    System.gc();

    this.startMemory = getUsedMemory();
    this.startTime = System.nanoTime();
  }

  @Override
  public void end() {
    this.endTime = System.nanoTime();
    this.endMemory = getUsedMemory();
  }

  @Override
  public AlgorithmMetrics getMetrics() {
    long time = endTime - startTime;
    long memory = Math.max(0, endMemory - startMemory);

    return AlgorithmMetrics.builder()
        .time(time)
        .memory(memory)
        .operations(operations)
        .iterations(iterations)
        .build();
  }

  private void reset() {
    this.operations = 0;
    this.iterations = 0;
    this.startTime = 0;
    this.startMemory = 0;
    this.endTime = 0;
    this.endMemory = 0;
  }

  private long warmup() {
    long sum = 0;
    for (int i = 0; i < warmupIterations; i++) {
      sum += i;
    }
    return sum;
  }

  private long getUsedMemory() {
    Runtime runtime = Runtime.getRuntime();
    return runtime.totalMemory() - runtime.freeMemory();
  }
}
