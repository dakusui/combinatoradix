package com.github.dakusui.enumerator;

import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import com.github.dakusui.enumerator.tuple.CartesianEnumerator;
import com.github.dakusui.enumerator.tuple.Tuple;
import com.github.dakusui.enumerator.tuple.TupleEnumerator;

public class CartesianEnumeratorTest {

  @Test
  public void test() {
    TupleEnumerator<String, String> enumerator = new CartesianEnumerator<String, String>(
        createValueSets(new String[][] { { "A", "B", "C", "D" }, { "a", "b" }, { "1", "2", "3" } }));
    int i = 0;
    for (Tuple<String, String> cur : enumerator) {
      System.out.println(String.format("%03d %s", i++, cur));
    }
  }

  SortedMap<String, List<String>> createValueSets(String[][] valueSets) {
    SortedMap<String, List<String>> ret = new TreeMap<String, List<String>>();
    int i = 1;
    for (String[] v : valueSets) {
      String key = String.format("key-%02d", i++);
      ret.put(key, Arrays.asList(v));
    }
    return ret;
  }
}
