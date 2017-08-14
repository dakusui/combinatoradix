package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CartesianEnumeratorAdaptorTest {
  @Test
  public void normal01() {
    Domains.Builder<String, Integer> builder = new Domains.Builder<String, Integer>();
    builder.addDomain("A", 1, 2, 3).addDomain("B", 1, 2).addDomain("C", 1, 2);

    CartesianEnumeratorAdaptor<Map<String, Integer>, String, Integer> cea = new SimpleCartesianEnumerator<String, Integer>(builder.build());

    assertEquals(12, cea.size());
    int numTuples = 0;
    for (Map<String, Integer> t : cea) {
      assertEquals(3, t.size());
      numTuples++;
    }

    assertEquals(12, numTuples);
  }

  @Test
  public void normal02() {
    Domains.Builder<String, Integer> builder = new Domains.Builder<String, Integer>();
    builder.addDomain("A", 1, 2, 3).addDomain("B", 1, 2).addDomain("C", 1, 2);

    CartesianEnumeratorAdaptor<Map<String, Integer>, String, Integer> cea = new SimpleCartesianEnumerator<String, Integer>(builder.build());

    assertEquals(12, cea.size());
    int numTuples = 0;
    for (int i = 0; i < cea.size(); i++) {
      Map<String, Integer> t = cea.get(i);
      assertEquals(3, t.size());
      numTuples++;
    }

    assertEquals(12, numTuples);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void error01() {
    Domains.Builder<String, Integer> builder = new Domains.Builder<String, Integer>();
    builder.addDomain("A", 1, 2, 3).addDomain("B", 1, 2).addDomain("C", 1, 2);

    CartesianEnumeratorAdaptor<Map<String, Integer>, String, Integer> cea = new SimpleCartesianEnumerator<String, Integer>(builder.build());
    ////
    // Should fail here with UnsupportedOperationException
    cea.iterator().remove();
  }
}