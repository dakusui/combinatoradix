package com.github.dakusui.combinatoradix.utils;

public enum Predicates {
  ;

  @SuppressWarnings("unchecked")
  public static <E> Predicate<E> notNull() {
    return Predicate.NOT_NULL;
  }

  public static <E extends Comparable<E>> Predicate<E> inRange(final E inclusive, final E exclusive) {
    return new Predicate<E>() {
      @Override
      public boolean test(E value) {
        return inclusive.compareTo(value) <= 0 && value.compareTo(exclusive) < 0;
      }
    };
  }
}
