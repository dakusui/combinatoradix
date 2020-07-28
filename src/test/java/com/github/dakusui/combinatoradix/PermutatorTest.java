package com.github.dakusui.combinatoradix;

import com.github.dakusui.combinatoradix.utils.InternalUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PermutatorTest extends EnumeratorTestBase {
  @Test
  public void givenA_1$whenRunPermutator$thenCorrect() {
    exerciseTest(singletonList(
        "a"
        ),
        1
    );
  }

  @Test
  public void givenA_0$whenRunPermutator$thenCorrect() {
    exerciseTest(singletonList(
        "a"
        ),
        0
    );
  }

  @Test
  public void givenABCD_4$whenRunPermutator$thenCorrect() {
    exerciseTest(asList(
        "a", "b", "c", "d"
        ),
        4
    );
  }

  @Test
  public void givenABCD_2$whenRunPermutator$thenCorrect() {
    exerciseTest(asList(
        "a", "b", "c", "d"
        ),
        2
    );
  }

  @Test
  public void givenABCD_1$whenRunPermutator$thenCorrect() {
    exerciseTest(asList(
        "a", "b", "c", "d"
        ),
        1
    );
  }

  @Test
  public void givenABCD_0$whenRunPermutator$thenCorrect() {
    exerciseTest(asList(
        "a", "b", "c", "d"
        ),
        0
    );
  }

  @Test
  public void givenABCD_5$whenRunPermutator$thenError() {
    exerciseTest(asList(
        "a", "b", "c", "d"
        ),
        5
    );
  }

  private void exerciseTest(List<String> symbols, int k) {
    final Permutator<String> permutator = new Permutator<>(
        symbols,
        k
    );
    final List<String> expected = new LinkedList<String>() {{
      for (int i = 0; i < permutator.size(); i++) {
        add(String.format("%2d:%s", i, permutator.get(i)));
      }
    }};
    List<String> actual = new LinkedList<String>() {{
      for (int i = 0; i < permutator.size(); i++) {
        add(String.format("%2d:%s", permutator.indexOf(permutator.get(i)), permutator.get(i)));
      }
    }};

    System.out.println("--");
    System.out.printf("%2s %-20s %-20s%n", "", "expected", "actual");
    for (int i = 0; i < permutator.size(); i++) {
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
    Iterator<List<String>> i = new Permutator<>(Collections.<String>emptyList(), 0).iterator();
    assertTrue(i.hasNext());
    assertEquals(emptyList(), i.next());
    assertFalse(i.hasNext());
  }

  @Test
  public void test_nP0() {
    Iterator<List<String>> p = new Permutator<>(testset1(), 0).iterator();
    assertTrue(p.hasNext());
    assertEquals(emptyList(), p.next());
    assertFalse(p.hasNext());
  }

  @Test
  public void test_nP1() {
    Iterator<List<String>> p = new Permutator<>(testset1(), 1).iterator();
    assertTrue(p.hasNext());
    assertEquals(singletonList("A"), p.next());
    assertTrue(p.hasNext());
    assertEquals(singletonList("B"), p.next());
    assertTrue(p.hasNext());
    assertEquals(singletonList("C"), p.next());
    assertTrue(p.hasNext());
    assertEquals(singletonList("D"), p.next());
    assertTrue(p.hasNext());
    assertEquals(singletonList("E"), p.next());
    assertFalse(p.hasNext());
  }

  @Test
  public void test_nP2() {
    Iterator<List<String>> p = new Permutator<>(testset1(), 2).iterator();
    assertTrue(p.hasNext());
    assertEquals(asList("A", "B"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("A", "C"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("A", "D"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("A", "E"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("B", "A"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("B", "C"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("B", "D"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("B", "E"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("C", "A"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("C", "B"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("C", "D"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("C", "E"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("D", "A"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("D", "B"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("D", "C"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("D", "E"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("E", "A"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("E", "B"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("E", "C"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("E", "D"), p.next());
    assertFalse(p.hasNext());
  }

  @Test
  public void test_nPn() {
    Iterator<List<String>> p = Enumerators.permutator(testset2(), 3).iterator();
    assertTrue(p.hasNext());
    assertEquals(asList("A", "B", "C"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("A", "C", "B"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("B", "A", "C"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("B", "C", "A"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("C", "A", "B"), p.next());
    assertTrue(p.hasNext());
    assertEquals(asList("C", "B", "A"), p.next());
    assertFalse(p.hasNext());
  }

  @Test
  public void test_forEach() {
    Enumerator.Base<String> p = new Permutator<>(testset2(), 3);
    int i = 0;
    for (List<String> entry : p) {
      assertEquals(3, entry.size());
      i++;
    }
    assertEquals(InternalUtils.nPk(3, 3), i);
  }
}
