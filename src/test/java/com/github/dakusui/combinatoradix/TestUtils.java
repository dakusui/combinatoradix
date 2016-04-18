package com.github.dakusui.combinatoradix;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class TestUtils {
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

  private TestUtils() {
  }
}
