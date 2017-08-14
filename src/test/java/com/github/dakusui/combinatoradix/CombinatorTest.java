package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CombinatorTest extends CombinatorTestBase {
  @Test
  public void given4SymbolsAnd2forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b", "c", "d"),
        2
    );
  }

  @Test
  public void given5SymbolsAnd2forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b", "c", "d", "e"),
        2
    );
  }


  @Test
  public void given4SymbolsAnd1forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b", "c", "d"),
        1
    );
  }

  @Test
  public void given4SymbolsAnd3forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b", "c", "d"),
        3
    );
  }

  @Test
  public void given5SymbolsAnd3forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b", "c", "d", "e"),
        3
    );
  }

  @Test
  public void given6SymbolsAnd4forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b", "c", "d", "e", "f"),
        4
    );
  }


  @Test
  public void given3SymbolsAnd3forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b", "c"),
        3
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
    assertEquals(singletonList("A"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(singletonList("B"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(singletonList("C"), combinator.next());
    assertEquals(true, combinator.hasNext());
    assertEquals(singletonList("D"), combinator.next());
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
    Enumerator<String> enumerator = Enumerators.combinator(singletonList("A"), 1);
    assertEquals(1, enumerator.size());
    assertEquals(singletonList("A"), enumerator.get(0));
  }

  @SuppressWarnings("unchecked")
  @Override
  <C extends Combinator<T>, T> C createCombinator(List<? extends T> symbols, int k) {
    return (C) new Combinator(symbols, k);
  }
}
