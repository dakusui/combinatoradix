package com.github.dakusui.combinatoradix.utils;

public interface ExceptionFactory<T extends Throwable> {
  ExceptionFactory<NullPointerException> NPE = new ExceptionFactory<NullPointerException>() {
    @Override
    public <TT extends NullPointerException> TT create(String format, Object... args) {
      throw new NullPointerException(String.format(format, args));
    }
  };

  ExceptionFactory<IllegalArgumentException> ILLEGAL_ARGUMENT = new ExceptionFactory<IllegalArgumentException>() {
    @Override
    public <TT extends IllegalArgumentException> TT create(String format, Object... args) {
      throw new IllegalArgumentException(String.format(format, args));
    }
  };

  ExceptionFactory<IndexOutOfBoundsException> INDEX_OUT_OF_BOUNDS = new ExceptionFactory<IndexOutOfBoundsException>() {
    @Override
    public <TT extends IndexOutOfBoundsException> TT create(String format, Object... args) {
      throw new IndexOutOfBoundsException(String.format(format, args));
    }
  };

  <TT extends T> TT create(String format, Object... args);

  enum Utils {
    ;

    public static ExceptionFactory<NullPointerException> npe() {
      return NPE;
    }

    public static ExceptionFactory<IllegalArgumentException> illegalArgument() {
      return ILLEGAL_ARGUMENT;
    }
    public static ExceptionFactory<IndexOutOfBoundsException> indexOutOfBounds() {
      return INDEX_OUT_OF_BOUNDS;
    }
  }
}
