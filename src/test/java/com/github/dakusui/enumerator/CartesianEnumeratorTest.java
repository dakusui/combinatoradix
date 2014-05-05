package com.github.dakusui.enumerator;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.github.dakusui.enumerator.tuple.AttrValue;
import com.github.dakusui.enumerator.tuple.CartesianEnumerator;

public class CartesianEnumeratorTest {

  @Test
  public void test() {
    List<AttrValue<String, String>> attrValues = new LinkedList<AttrValue<String, String>>();
    attrValues.add(attrValue("key1", "A"));
    attrValues.add(attrValue("key1", "B"));
    attrValues.add(attrValue("key2", "a"));
    attrValues.add(attrValue("key2", "b"));
    @SuppressWarnings("unchecked")
    Enumerator<AttrValue<String, String>> enumerator = new CartesianEnumerator<String, String>(attrValues);
    int i = 0;
    for (List<AttrValue<String, String>> cur : enumerator) {
      System.out.println(String.format("%03d %s", i++, cur));
    }
  }

  static AttrValue<String, String> attrValue(String attr, String value) {
    return new AttrValue<String, String>(attr, value);
  }
}
