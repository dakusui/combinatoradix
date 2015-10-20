package com.github.dakusui.combinatoradix;

import java.util.List;

public enum Enumerators {
  ;
  public static <T> Enumerator<T> shuffler(List<? extends T> list, long size, long randomSeed) {
    return new Shuffler<T>(list, size, randomSeed);
  }

  public static <T> Combinator<T> combinator(List<? extends T> list, int k) {
    return new Combinator<T>(list, k);
  }

  public static <T> Cartesianator<T> cartesianator(List<? extends List<? extends T>> lists) {
    return new Cartesianator<T>(lists);
  }
}
