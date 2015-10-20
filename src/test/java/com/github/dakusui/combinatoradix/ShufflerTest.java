package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ShufflerTest {
  @Test
  public void shuffler() {
    Enumerator<String> shuffler =  Enumerators.shuffler(new LinkedList<String>(Arrays.asList("1", "2", "3")), 3, 4649);
    assertEquals(3, shuffler.size());
    for (Object each : shuffler) {
      System.out.println(each);
    }
    assertArrayEquals(new Object[]{"2", "1", "3"}, shuffler.get(0).toArray());
    assertArrayEquals(new Object[]{"1", "2", "3"}, shuffler.get(1).toArray());
    assertArrayEquals(new Object[]{"1", "2", "3"}, shuffler.get(2).toArray());
  }
}
