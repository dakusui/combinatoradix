package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CombinatorTest extends EnumeratorTestBase {

  @Test
  public void test_empty() {
    // Empty set should result in empty iterator immediately
    Iterator<List<String>> i = new Combinator<String>(Collections.<String>emptyList(), 0).iterator();
    assertTrue(i.hasNext());
    assertEquals(Collections.emptyList(), i.next());
    assertFalse(i.hasNext());
  }

  @Test
  public void test_nC0() {
    Iterator<List<String>> combinator = new Combinator<String>(testset1(), 0).iterator();
    assertEquals(true, combinator.hasNext());
    assertEquals(asList(), combinator.next());
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
    Iterator<List<String>> combinator = new Combinator<String>(testset1(), 3).iterator();
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
