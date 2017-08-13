package com.github.dakusui.combinatoradix;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CombinatorTest extends EnumeratorTestBase {
  public static void main(String... args) {
    exerciseTest(
        asList("a", "b", "c", "d", "e"),
        2
    );
  }

  private static void exerciseTest(List<String> in, int k) {
    final Combinator<String> combinator = new Combinator<String>(
        in,
        k
    );
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

  @Test
  public void test_empty() {
    // Empty set should result in empty iterator immediately
    Iterator<List<String>> i = new Combinator<String>(Collections.<String>emptyList(), 0).iterator();
    assertTrue(i.hasNext());
    assertEquals(emptyList(), i.next());
    assertFalse(i.hasNext());
  }

  @Test
  public void test_nC0() {
    Iterator<List<String>> combinator = new Combinator<String>(testset1(), 0).iterator();
    assertEquals(true, combinator.hasNext());
    assertEquals(emptyList(), combinator.next());
    assertEquals(false, combinator.hasNext());
  }

  @Test
  public void test_nC1() {
    Iterator<List<String>> combinator = new Combinator<String>(testset1(), 1).iterator();
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("B"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("C"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("D"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("E"), combinator.next());
    assertEquals(false, combinator.hasNext());
  }

  @Test
  public void test_nC2() {
    Iterator<List<String>> combinator = new Combinator<String>(testset1(), 2).iterator();
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A", "B"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A", "C"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A", "D"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A", "E"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("B", "C"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("B", "D"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("B", "E"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("C", "D"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("C", "E"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("D", "E"), combinator.next());
    assertEquals(false, combinator.hasNext());
  }

  @Test
  public void test_nC3() {
    Iterator<List<String>> combinator = Enumerators.combinator(testset1(), 3).iterator();
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A", "B", "C"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A", "B", "D"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A", "B", "E"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A", "C", "D"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A", "C", "E"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("A", "D", "E"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("B", "C", "D"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("B", "C", "E"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("B", "D", "E"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(asList("C", "D", "E"), combinator.next());
    assertEquals(false, combinator.hasNext());
  }

  @Test
  public void test_1C1() {
    Enumerator<String> enumerator = Enumerators.combinator(asList("A"), 1);
    assertEquals(1, enumerator.size());
    assertEquals(asList("A"), enumerator.get(0));
  }
}
