package com.github.dakusui.combinatoradix;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class HomogeniousCombinatorTest extends CombinatorTestBase {
  @Test
  public void given2SymbolsAnd2forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b"),
        2
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
  public void given3SymbolsAnd2forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b", "c"),
        2
    );
  }

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
  public void given6SymbolsAnd2forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b", "c", "d", "e", "f"),
        2
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
  public void given6SymbolsAnd3forK$whenRunCombinator$thenCorrect() {
    exerciseTest(
        asList("a", "b", "c", "d", "e", "f"),
        3
    );
  }

  @Test
  public void test_empty() {
    // Empty set should result in empty iterator immediately
    Iterator<List<String>> i = new HomogeniousCombinator<>(Collections.<String>emptyList(), 0).iterator();
    assertTrue(i.hasNext());
    assertEquals(emptyList(), i.next());
    assertFalse(i.hasNext());
  }

  @Test
  public void test_nH1() {
    Iterator<List<String>> homogeniousCombinator = new HomogeniousCombinator<>(testset1(), 1).iterator();
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(singletonList("A"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(singletonList("B"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(singletonList("C"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(singletonList("D"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(singletonList("E"), homogeniousCombinator.next());
    assertFalse(homogeniousCombinator.hasNext());
  }

  @Test
  public void test_nH2() {
    Iterator<List<String>> homogeniousCombinator = Enumerators.homogeniousCombinator(testset1(), 2).iterator();
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("A", "A"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("A", "B"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("A", "C"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("A", "D"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("A", "E"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("B", "B"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("B", "C"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("B", "D"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("B", "E"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("C", "C"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("C", "D"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("C", "E"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("D", "D"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("D", "E"), homogeniousCombinator.next());
    assertTrue(homogeniousCombinator.hasNext());
    assertEquals(asList("E", "E"), homogeniousCombinator.next());
    assertFalse(homogeniousCombinator.hasNext());
  }

  public static void main(String[] args) {
    List<String> items = new LinkedList<>();
    items.add("A");
    items.add("B");
    items.add("C");
    items.add("D");
    items.add("E");

    Iterator<List<String>> enumerator = new HomogeniousCombinator<>(items, 2).iterator();
    int i = 0;
    while (enumerator.hasNext()) {
      System.out.println(i + ":" + enumerator.next());
      //enumerator.next();
      i++;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  <C extends Combinator<T>, T> C createCombinator(List<? extends T> symbols, int k) {
    return (C) new HomogeniousCombinator<T>(symbols, k);
  }
}
