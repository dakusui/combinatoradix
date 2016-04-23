package com.github.dakusui.combinatoradix;

import java.util.List;

public enum Enumerators {
  ;
  public static <T> Shuffler<T> shuffler(List<? extends T> list, long size, long randomSeed) {
    return new Shuffler<T>(list, size, randomSeed);
  }

  public static <T> Permutator<T> permutator(List<? extends T> list, int k) {
    return new Permutator<T>(list, k);
  }

  public static <T> Combinator<T> combinator(List<? extends T> list, int k) {
    return new Combinator<T>(list, k);
  }

  public static <T> HomogeniousCombinator<T> homogeniousCombinator(List<? extends T> list, int k) {
    return new HomogeniousCombinator<T>(list, k);
  }


  public static <T> Cartesianator<T> cartesianator(List<? extends List<? extends T>> lists) {
    return new Cartesianator<T>(lists);
  }
}
