package com.github.dakusui.combinatoradix;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class CombinatorTestBase extends EnumeratorTestBase {
  @Test
  public void encode() {
    // 5C1 - 5    4C1 - 4   3C1 - 3  2C1 - 2
    // 5C2 - 10   4C2 - 6   3C2 - 3
    // 5C3 - 10   4C3 - 4
    // 5C4 - 5

    // [1,-]   - 3
    // [2,-]   - 3 + 2 = 5
    // [1,-,-] -       = 6 = 4C2
    // [2,-,-] -       = 9 = 4C2+3C2
    encode(createCombinator(
        asList("a", "b", "c", "d", "e"),
        3
    ));
  }

  private static void encode(Combinator<String> combinator) {
    for (int i = 0; i < combinator.size(); i++) {
      System.out.printf("%d:%s:%s%n", i, combinator.get(i), Arrays.toString(combinator.encode(combinator.getElement(i))));
    }
  }

  void exerciseTest(List<String> in, int k) {
    final Combinator<String> combinator = createCombinator(in, k);
    System.out.println("--");
    encode(createCombinator(in, k));

    final List<String> expected = new LinkedList<String>() {{
      for (int i = 0; i < combinator.size(); i++) {
        add(String.format("%2d:%s", i, combinator.get(i)));
      }
    }};
    List<String> actual = new LinkedList<String>() {{
      for (int i = 0; i < combinator.size(); i++) {
        add(String.format("%2d:%s", combinator.indexOf(combinator.get(i)), combinator.get(i)));
      }
    }};

    System.out.println("--");
    System.out.printf("%2s %-20s %-20s%n", "", "expected", "actual");
    for (int i = 0; i < combinator.size(); i++) {
      System.out.printf(
          "%2s %-20s %-20s%n",
          expected.get(i).equals(actual.get(i)) ?
              "" :
              "NG",
          expected.get(i),
          actual.get(i));
    }

    assertThat(
        actual.toString(),
        CoreMatchers.equalTo(expected.toString())
    );
  }

  abstract <C extends Combinator<T>, T> C createCombinator(List<? extends T> symbols, int k);
}
