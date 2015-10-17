package com.github.dakusui.combinatoradix;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.github.dakusui.combinatoradix.tuple.AttrValue;
import com.github.dakusui.combinatoradix.tuple.CartesianEnumerator;

import static org.junit.Assert.assertEquals;

public class CartesianEnumeratorTest {
  static {
    // System.setProperty("COMBINATORADIX_DEBUG", "true");
  }

  @Test
  public void test() {
    List<AttrValue<String, String>> attrValues = new LinkedList<AttrValue<String, String>>();
    attrValues.add(attrValue("key1", "A"));
    attrValues.add(attrValue("key1", "B"));
    attrValues.add(attrValue("key2", "a"));
    attrValues.add(attrValue("key2", "b"));
    attrValues.add(attrValue("key3", "X"));
    attrValues.add(attrValue("key3", "Y"));
    @SuppressWarnings("unchecked")
    Enumerator<AttrValue<String, String>> enumerator = new CartesianEnumerator<String, String>(attrValues);
    int i = 0;
    for (List<AttrValue<String, String>> cur : enumerator) {
      Utils.stdout.println(String.format("%03d %s", i++, cur));
    }
    assertEquals(8, enumerator.size());
  }

  static AttrValue<String, String> attrValue(String attr, String value) {
    return new AttrValue<String, String>(attr, value);
  }
}
