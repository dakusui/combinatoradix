package com.github.dakusui.combinatoradix;

public enum Enumerators {
  ;

  public static long nPk(long n, long k) {
    long ret = 1;
    for (long i = n; i > n - k; i--) {
      if (i > Long.MAX_VALUE / ret) {
        throw new IllegalArgumentException(String.format("Overflow. Too big numbers are used %sP%s: %d * %d", n, k, ret, i));
      }
      ret *= i;
    }
    return ret;
  }

  public static long nHk(int n, int k) {
    return nCk(n + k - 1, k);
  }

  public static long nCk(long n, long k) {
    long ret = 1;
    long j = 1;
    for (long i = n; i > n - k; i--) {
      if (i > Long.MAX_VALUE / ret) {
        throw new IllegalArgumentException(String.format("Overflow. Too big numbers are used %sC%s: %d * %d", n, k, ret, i));
      }
      ret *= i;
      ret /= j;
      j++;
    }
    return ret;
  }
}
