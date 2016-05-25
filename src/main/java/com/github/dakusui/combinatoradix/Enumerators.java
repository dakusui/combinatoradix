package com.github.dakusui.combinatoradix;

import java.util.List;
import java.util.Random;

public enum Enumerators {
  ;
  public static <T> Shuffler<T> shuffler(List<? extends T> list, long size, long randomSeed) {
    return shuffler(list, size, new Random(randomSeed));
  }

  public static <T> Shuffler<T> shuffler(List<? extends T> list, long size, Random random) {
    return new Shuffler<T>(list, size, random);
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
