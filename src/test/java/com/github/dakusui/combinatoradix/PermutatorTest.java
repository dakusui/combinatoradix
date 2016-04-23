package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PermutatorTest extends EnumeratorTestBase {
  @Test
  public void test_empty() {
    // Empty set should result in empty iterator immediately
    Iterator<List<String>> i = new Permutator<String>(Collections.<String>emptyList(), 0).iterator();
    assertTrue(i.hasNext());
    assertEquals(Collections.emptyList(), i.next());
    assertFalse(i.hasNext());
  }

  @Test
  public void test_nP0() {
    Iterator<List<String>> p = new Permutator<String>(testset1(), 0).iterator();
    assertEquals(true, p.hasNext());
    assertEquals(asList(), p.next());
    assertEquals(false, p.hasNext());
  }

  @Test
  public void test_nP1() {
    Iterator<List<String>> p = new Permutator<String>(testset1(), 1).iterator();
    assertEquals(true, p.hasNext());
    assertEquals(asList("A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("D"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("E"), p.next());
    assertEquals(false, p.hasNext());
  }

  @Test
  public void test_nP2() {
    Iterator<List<String>> p = new Permutator<String>(testset1(), 2).iterator();
    assertEquals(true, p.hasNext());
    assertEquals(asList("A", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("A", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("A", "D"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("A", "E"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("B", "A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("B", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("B", "D"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("B", "E"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("C", "A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("C", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("C", "D"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("C", "E"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("D", "A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("D", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("D", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("D", "E"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("E", "A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("E", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("E", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("E", "D"), p.next());
    assertEquals(false, p.hasNext());
  }

  @Test
  public void test_nPn() {
    Iterator<List<String>> p = Enumerators.permutator(testset2(), 3).iterator();
    assertEquals(true, p.hasNext());
    assertEquals(asList("A", "B", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("A", "C", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("B", "A", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("B", "C", "A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("C", "A", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(asList("C", "B", "A"), p.next());
    assertEquals(false, p.hasNext());
  }

  @Test
  public void test_forEach() {
    Enumerator.Base<String> p = new Permutator<String>(testset2(), 3);
    int i = 0;
    for (List<String> entry : p) {
      assertEquals(3, entry.size());
      i++;
    }
    assertEquals(Utils.nPk(3, 3), i);
  }
}
