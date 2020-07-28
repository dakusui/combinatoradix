package com.github.dakusui.combinatoradix;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class EnumeratorErrorTest {
  Enumerator.Base<String> enumerator = new Permutator<>(Collections.singletonList("Hello"), 1);

  @Test
  public void goOverLimitByNextMethod() {
    Iterator<List<String>> i = enumerator.iterator();
    assertTrue(i.hasNext());
    assertEquals("Hello", i.next().get(0));
    assertFalse(i.hasNext());
    assertThrows(NoSuchElementException.class, i::next);
  }

  @Test
  public void goOverLimitByGetMethod() {
    assertEquals(1, enumerator.size());
    assertThrows(IndexOutOfBoundsException.class, () -> enumerator.get(1));
  }

  @Test
  public void tryToRemoveAndMakeSureExceptionIsThrown() {
    assertThrows(UnsupportedOperationException.class, () -> enumerator.iterator().remove());
  }
}
