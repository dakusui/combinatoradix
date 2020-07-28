package com.github.dakusui.combinatoradix;

import com.github.dakusui.combinatoradix.tuple.AttrValue;
import com.github.dakusui.combinatoradix.tuple.CartesianEnumerator;
import com.github.dakusui.combinatoradix.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartesianEnumeratorTest {
  @Test
  public void test() {
    List<AttrValue<String, String>> attrValues = new LinkedList<>();
    attrValues.add(attrValue("key1", "A"));
    attrValues.add(attrValue("key1", "B"));
    attrValues.add(attrValue("key2", "a"));
    attrValues.add(attrValue("key2", "b"));
    attrValues.add(attrValue("key3", "X"));
    attrValues.add(attrValue("key3", "Y"));
    Enumerator.Base<AttrValue<String, String>> enumerator = new CartesianEnumerator<>(attrValues);
    int i = 0;
    for (List<AttrValue<String, String>> cur : enumerator) {
      TestUtils.stdout.println(String.format("%03d %s", i++, cur));
    }
    assertEquals(8, enumerator.size());
  }

  @Test
  public void testBig() {
    // Should be able to handle 62 attributes
    List<AttrValue<String, String>> attrValues = new LinkedList<>();
    for (int i = 0; i < 62; i++) {
      attrValues.add(attrValue("key" + i, "A"));
      attrValues.add(attrValue("key" + i, "B"));
    }
    new CartesianEnumerator<>(attrValues);
  }

  @Test
  public void testTooBig() {
    // 63rd attributes cannot be handled.
    List<AttrValue<String, String>> attrValues = new LinkedList<>();
    for (int i = 0; i < 63; i++) {
      attrValues.add(attrValue("key" + i, "A"));
      attrValues.add(attrValue("key" + i, "B"));
    }
    assertThrows(IllegalArgumentException.class, () -> new CartesianEnumerator<>(attrValues));
  }

  @Test
  public void testAttrValueEquals() {
    assertEquals(attrValue("a", "V"), attrValue("a", "V"));
  }

  @Test
  public void testAttrValueNotEquals1() {
    assertNotEquals(attrValue("a", "V"), attrValue("a", "W"));
  }

  @Test
  public void testAttrValueNotEquals2() {
    //Intentionally comparing with an object of wrong type.
    assertNotEquals(attrValue("a", "V"), new Object());
  }


  static AttrValue<String, String> attrValue(String attr, String value) {
    return new AttrValue<>(attr, value);
  }

  public static void main(String... args) {
    List<AttrValue<String, String>> attrValues = new LinkedList<>();
    attrValues.add(attrValue("key1", "A"));
    attrValues.add(attrValue("key1", "B"));
    attrValues.add(attrValue("key2", "a"));
    attrValues.add(attrValue("key2", "b"));
    attrValues.add(attrValue("key3", "X"));
    attrValues.add(attrValue("key3", "Y"));

    Enumerator<AttrValue<String, String>> enumerator = new CartesianEnumerator<>(attrValues);

    for (int i = 5; i < enumerator.size(); i++) {
      System.out.printf("%s: %s -> %s : %s%n", i, enumerator.get(i), enumerator.indexOf(enumerator.get(i)), enumerator.get(enumerator.indexOf(enumerator.get(i))));
    }
  }
}
