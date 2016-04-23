package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

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
    //noinspection unchecked
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
    Enumerator<String> c = Enumerators.cartesianator(singletonList(
        new LinkedList<String>()
    ));
    assertEquals(0, c.size());
    Iterator<List<String>> i = c.iterator();
    assertEquals(false, i.hasNext());
  }

  @Test
  public void boundary1$1_0$1() {
    //noinspection unchecked
    Enumerator<String> c = Enumerators.cartesianator(asList(
        asList("A1", "A2"),
        new LinkedList<String>()
    ));
    assertEquals(0, c.size());
    Iterator<List<String>> i = c.iterator();
    assertEquals(false, i.hasNext());
  }

  @Test(expected = NoSuchElementException.class)
  public void givenEmptyIterator$whenNextIsAttemptedAfterLast$thenNoSuchElementThrown() {
    ////
    // Given: iterator is at the last
    Enumerator<String> c = Enumerators.cartesianator(singletonList(
        new LinkedList<String>()
    ));
    assertEquals(0, c.size());
    Iterator<List<String>> i = c.iterator();
    assertEquals(false, i.hasNext());

    i.next();
  }


  @Test(expected = NoSuchElementException.class)
  public void givenNonEmptyIterator$whenNextIsAttemptedAfterLast$thenNoSuchElementThrown() {
    ////
    // Given: iterator is at the last
    Enumerator<String> c = Enumerators.cartesianator(singletonList(
        singletonList("A1")
    ));
    assertEquals(1, c.size());
    Iterator<List<String>> i = c.iterator();
    assertEquals(true, i.hasNext());
    i.next();
    assertEquals(false, i.hasNext());
    ////
    // When: next() is attempted after last
    i.next();
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void givenEnumerator$whenGetAfterLast$thenExceptionThrown() {
    ////
    // Given: enumerator
    Enumerator<String> c = Enumerators.cartesianator(singletonList(
        singletonList("A1")
    ));
    assertEquals(1, c.size());
    ////
    // When:
    c.get(1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenTooManyNonEmptyLists$whenNewCartesinator$thenExceptionThrown() {
    ////
    // Given:
    List<List<String>> lists = new LinkedList<List<String>>();
    for (int i = 0; i < 100; i++) {
      List<String> each = new LinkedList<String>();
      each.add("I" + i + "-" + 0);
      each.add("I" + i + "-" + 1);
      lists.add(each);
    }

    ////
    // When: New Cartesinator
    Enumerator<String> c = Enumerators.cartesianator(lists);
    assertEquals(1, c.size());
  }

}
