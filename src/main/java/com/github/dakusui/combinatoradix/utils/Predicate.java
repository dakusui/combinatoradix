package com.github.dakusui.combinatoradix.utils;

public interface Predicate<T> {
  Predicate NOT_NULL = new Predicate() {
    @Override
    public boolean test(Object value) {
      return value != null;
    }
  };

  boolean test(T value);

}
