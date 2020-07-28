package com.github.dakusui.combinatoradix;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class CartesianatorTest {
  @Test
  public void normal1$1() {
    Enumerator<String> c = Enumerators.cartesianator(singletonList(
        singletonList("A1")
    ));
    assertEquals(1, c.size());
    Iterator<List<String>> i = c.iterator();
    assertEquals(singletonList("A1"), i.next());
  }

  @Test
  public void normal2$1() {
    Enumerator<String> c = Enumerators.cartesianator(singletonList(
        asList("A1", "A2")
    ));
    assertEquals(2, c.size());
    Iterator<List<String>> i = c.iterator();
    assertEquals(singletonList("A1"), i.next());
    assertEquals(singletonList("A2"), i.next());
  }

  @Test
  public void normal2$3() {
    Enumerator<String> c = Enumerators.cartesianator(asList(
        asList("A1", "A2"),
        asList("B1", "B2"),
        asList("C1", "C2")
    ));
    assertEquals(8, c.size());
    Iterator<List<String>> i = c.iterator();
    assertEquals(asList("A1", "B1", "C1"), i.next());
    assertEquals(asList("A1", "B1", "C2"), i.next());
    i.next();
    i.next();
    i.next();
    i.next();
    i.next();
    assertEquals(asList("A2", "B2", "C2"), i.next());
  }

  @Test
  public void boundary0$1() {
    Enumerator<String> c = Enumerators.cartesianator(singletonList(new LinkedList<>()));
    assertEquals(0, c.size());
    Iterator<List<String>> i = c.iterator();
    assertFalse(i.hasNext());
  }

  @Test
  public void boundary1$1_0$1() {
    Enumerator<String> c = Enumerators.cartesianator(asList(
        asList("A1", "A2"),
        new LinkedList<>()
    ));
    assertEquals(0, c.size());
    Iterator<List<String>> i = c.iterator();
    assertFalse(i.hasNext());
  }

  @Test
  public void givenEmptyIterator$whenNextIsAttemptedAfterLast$thenNoSuchElementThrown() {
    ////
    // Given: iterator is at the last
    Enumerator<String> c = Enumerators.cartesianator(singletonList(
        new LinkedList<>()
    ));
    assertEquals(0, c.size());
    Iterator<List<String>> i = c.iterator();
    assertFalse(i.hasNext());

    assertThrows(NoSuchElementException.class, i::next);
  }


  @Test
  public void givenNonEmptyIterator$whenNextIsAttemptedAfterLast$thenNoSuchElementThrown() {
    ////
    // Given: iterator is at the last
    Enumerator<String> c = Enumerators.cartesianator(singletonList(
        singletonList("A1")
    ));
    assertEquals(1, c.size());
    Iterator<List<String>> i = c.iterator();
    assertTrue(i.hasNext());
    i.next();
    assertFalse(i.hasNext());
    ////
    // When: next() is attempted after last
    assertThrows(NoSuchElementException.class, i::next);
  }

  @Test
  public void givenEnumerator$whenGetAfterLast$thenExceptionThrown() {
    ////
    // Given: enumerator
    Enumerator<String> c = Enumerators.cartesianator(singletonList(
        singletonList("A1")
    ));
    assertEquals(1, c.size());
    ////
    // Then:
    assertThrows(IndexOutOfBoundsException.class, () -> /* whrn */c.get(1));
  }

  @Test
  public void givenTooManyNonEmptyLists$whenNewCartesinator$thenExceptionThrown() {
    ////
    // Given:
    List<List<String>> lists = new LinkedList<>();
    for (int i = 0; i < 100; i++) {
      List<String> each = new LinkedList<>();
      each.add("I" + i + "-" + 0);
      each.add("I" + i + "-" + 1);
      lists.add(each);
    }

    ////
    // When: New Cartesinator
    assertThrows(IllegalArgumentException.class, () -> {
      Enumerator<String> c = Enumerators.cartesianator(lists);
      assertEquals(1, c.size());
    });
  }

}
