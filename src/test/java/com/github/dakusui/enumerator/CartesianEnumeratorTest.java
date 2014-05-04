package com.github.dakusui.enumerator;

import java.util.List;

import org.junit.Test;

import com.github.dakusui.enumerator.tuple.AttrValue;
import com.github.dakusui.enumerator.tuple.CartesianEnumerator;

public class CartesianEnumeratorTest {

  @Test
  public void test() {
    @SuppressWarnings("unchecked")
    Enumerator<AttrValue<String, String>> enumerator = new CartesianEnumerator<String, String>(
        attrValue("key1", "A"), attrValue("key1", "B"), attrValue("key2", "a"),
        attrValue("key2", "b"));
    int i = 0;
    for (List<AttrValue<String, String>> cur : enumerator) {
      System.out.println(String.format("%03d %s", i++, cur));
    }
  }

  static AttrValue<String, String> attrValue(String attr, String value) {
    return new AttrValue<String, String>(attr, value);
  }
}
