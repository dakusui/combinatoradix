package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BigNumberTest {
  private static final List<String> dataSet = Arrays.asList(
      /*
       1    2    3    4    5    6    7    8    9   10
       */
      "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
      "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
      "U", "V", "W", "X", "Y", "Z"
  );

  @Test(expected = IllegalArgumentException.class)
  public void testPermutator() {
    System.out.println("-:" + Long.MAX_VALUE);
    try {
      for (int i = 0; i < dataSet.size(); i++) {
        Enumerator<String> e = new Permutator<String>(dataSet, i);
        System.out.println(i + ":" + e.size());
      }
    } catch (IllegalArgumentException e) {
      assertEquals("Overflow. Too big numbers are used 26P15: 841941782922240000 * 12", e.getMessage());
      throw e;
    }
  }

  @Test
  public void testCombinator() {
    for (int i = 0; i < dataSet.size(); i++) {
      Enumerator<String> e = new Combinator<String>(dataSet, i);
      System.out.println(i + ":" + e.size());
    }
  }

  @Test
  public void testRepeatedCombinator() {
    for (int i = 0; i < dataSet.size(); i++) {
      Enumerator<String> e = new HomogeniousCombinator<String>(dataSet, i);
      System.out.println(i + ":" + e.size());
    }
  }
}
