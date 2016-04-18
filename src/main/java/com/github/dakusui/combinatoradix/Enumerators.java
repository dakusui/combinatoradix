package com.github.dakusui.combinatoradix;

import java.util.List;

public enum Enumerators {
  ;
  public static <T> Enumerator.Base<T> shuffler(List<? extends T> list, long size, long randomSeed) {
    return new Shuffler<T>(list, size, randomSeed);
  }
}
