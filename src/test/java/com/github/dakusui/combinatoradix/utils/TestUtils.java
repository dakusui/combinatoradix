package com.github.dakusui.combinatoradix.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public enum TestUtils {
  ;

  static final PrintStream STDOUT = System.out;
  static final PrintStream STDERR = System.err;
  public static final PrintStream stdout = createStdOut();

  private static PrintStream createStdOut() {
    if (System.getProperty("COMBINATORADIX_DEBUG") == null) {
      return new PrintStream(new OutputStream() {
        @Override
        public void write(int b) throws IOException {
        }
      });
    } else {
      return System.out;
    }
  }

  public static void suppressStdOutErrIfRunUnderSurefire() {
    if (TestUtils.isRunUnderSurefire()) {
      System.setOut(new PrintStream(new OutputStream() {
        @Override
        public void write(int b) throws IOException {
        }
      }));
      System.setErr(new PrintStream(new OutputStream() {
        @Override
        public void write(int b) throws IOException {
        }
      }));
    }
  }

  public static void restoreStdOutErr() {
    System.setOut(STDOUT);
    System.setErr(STDERR);
  }

  public static boolean isRunUnderSurefire() {
    return System.getProperty("surefire.real.class.path") != null;
  }
}
