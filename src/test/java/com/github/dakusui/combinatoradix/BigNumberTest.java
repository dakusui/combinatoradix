package com.github.dakusui.combinatoradix;

import com.github.dakusui.combinatoradix.utils.InternalUtils;
import com.github.dakusui.combinatoradix.utils.TestBase;
import com.github.dakusui.combinatoradix.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BigNumberTest extends TestBase {
  private static final List<String> dataSet = Arrays.asList(
      /*
       1    2    3    4    5    6    7    8    9   10
       */
      "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
      "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
      "U", "V", "W", "X", "Y", "Z"
  );

  @Test
  public void testPermutator() {
    System.out.println("-:" + Long.MAX_VALUE);
    Throwable t = assertThrows(IllegalArgumentException.class, () -> {
      for (int i = 0; i < dataSet.size(); i++) {
        Enumerator.Base<String> e = new Permutator<>(dataSet, i);
        TestUtils.stdout.println(i + ":" + e.size());
      }
    });
    assertEquals("Overflow. Too big numbers are used 26P15: 841941782922240000 * 12", t.getMessage());
  }


  @Test
  public void testCombinator() {
    for (int i = 0; i < dataSet.size(); i++) {
      Enumerator.Base<String> e = new Combinator<>(dataSet, i);
      TestUtils.stdout.println(i + ":" + e.size());
    }
  }

  @Test
  public void testRepeatedCombinator() {
    for (int i = 0; i < dataSet.size(); i++) {
      Enumerator.Base<String> e = new HomogeniousCombinator<>(dataSet, i);
      TestUtils.stdout.println(i + ":" + e.size());
    }
  }

  @Test
  public void test1000000thWord_Permutation() {
    assertEquals("[D, I, K, H, Q]", new Permutator<>(dataSet, 5).get(1000000).toString());
  }

  @Test
  public void test1000000thWord_Combination() {
    assertEquals("[C, G, H, K, U, X, Y, Z]", new Combinator<>(dataSet, 8).get(1000000).toString());
  }

  @Test
  public void test1000000thWord_RepeatedCombination() {
    assertEquals("[A, B, D, G, R, T, V, Z]", new HomogeniousCombinator<>(dataSet, 8).get(1000000).toString());
  }

  @Test
  public void test100C50() {
    assertThrows(IllegalArgumentException.class, () -> InternalUtils.nCk(100, 50));
  }
}
