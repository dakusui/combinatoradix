package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CartesianEnumeratorAdaptorTest {
  @Test
  public void normal01() {
    Domains.Builder<String, Integer> builder = new Domains.Builder<String, Integer>();
    builder.addDomain("A", 1, 2, 3).addDomain("B", 1, 2).addDomain("C", 1, 2);

    CartesianEnumeratorAdaptor<HashMap<String, Integer>, String, Integer> cea = new SimpleCartesianEnumerator(builder.build());

    assertEquals(12, cea.size());
    int numTuples = 0;
    for (Map<String, Integer> t : cea) {
      assertEquals(3, t.size());
      numTuples++;
    }
    assertEquals(12, numTuples);
  }
}
