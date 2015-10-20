package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
    assertEquals(toList(), p.next());
    assertEquals(false, p.hasNext());
  }

  @Test
  public void test_nP1() {
    Iterator<List<String>> p = new Permutator<String>(testset1(), 1).iterator();
    assertEquals(true, p.hasNext());
    assertEquals(toList("A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("D"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("E"), p.next());
    assertEquals(false, p.hasNext());
  }

  @Test
  public void test_nP2() {
    Iterator<List<String>> p = new Permutator<String>(testset1(), 2).iterator();
    assertEquals(true, p.hasNext());
    assertEquals(toList("A", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("A", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("A", "D"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("A", "E"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("B", "A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("B", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("B", "D"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("B", "E"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("C", "A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("C", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("C", "D"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("C", "E"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("D", "A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("D", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("D", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("D", "E"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("E", "A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("E", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("E", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("E", "D"), p.next());
    assertEquals(false, p.hasNext());
  }

  @Test
  public void test_nPn() {
    Iterator<List<String>> p = new Permutator<String>(testset2(), 3).iterator();
    assertEquals(true, p.hasNext());
    assertEquals(toList("A", "B", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("A", "C", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("B", "A", "C"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("B", "C", "A"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("C", "A", "B"), p.next());
    assertEquals(true, p.hasNext());
    assertEquals(toList("C", "B", "A"), p.next());
    assertEquals(false, p.hasNext());
  }

  @Test
  public void test_forEach() {
    Enumerator<String> p = new Permutator<String>(testset2(), 3);
    int i = 0;
    for (List<String> entry : p) {
      assertEquals(3, entry.size());
      i++;
    }
    assertEquals(Enumerator.nPk(3, 3), i);
  }
}
