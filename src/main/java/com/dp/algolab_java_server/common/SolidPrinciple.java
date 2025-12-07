package com.dp.algolab_java_server.common;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.Repeatable;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR })
@Repeatable(SolidPrinciple.List.class)
public @interface SolidPrinciple {
  String name();

  String solvedProblem();

  @Retention(RetentionPolicy.SOURCE)
  @Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR })
  @interface List {
    SolidPrinciple[] value();
  }
}
