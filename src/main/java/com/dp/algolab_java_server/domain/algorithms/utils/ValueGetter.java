package com.dp.algolab_java_server.domain.algorithms.utils;

@FunctionalInterface
public interface ValueGetter<T> {
  int getValue(T item);
}
