package com.github.dakusui.combinatoradix.tuple;

import com.github.dakusui.combinatoradix.Enumerator;
import com.github.dakusui.combinatoradix.Utils;

import java.util.*;

public class CartesianEnumerator<T, U> extends Enumerator.Base<AttrValue<T, U>> {

  private final ArrayList<T> attrsInReverseOrder;
  private Map<T, List<AttrValue<T, U>>> attrValues = new HashMap<T, List<AttrValue<T, U>>>();

  @SuppressWarnings("unchecked")
  public CartesianEnumerator(List<AttrValue<T, U>> attributeValues) {
    super(
        attributeValues,
        countAttributes(attributeValues.toArray(new AttrValue[attributeValues.size()])),
        calculateSize(attributeValues)
    );
    this.attrsInReverseOrder = new ArrayList<T>(this.k);
    for (AttrValue<T, U> cur : this.items) {
      if (!this.attrsInReverseOrder.contains(cur.attr())) {
        this.attrsInReverseOrder.add(cur.attr());
      }
    }
    this.attrValues = attrValues(attributeValues);
    Collections.reverse(this.attrsInReverseOrder);
  }

  private static int countAttributes(AttrValue<Object, Object>[] attributeValues) {
    Set<AttrValue<?, ?>> attrs = new HashSet<AttrValue<?, ?>>();
    Collections.addAll(attrs, attributeValues);
    return attrs.size();
  }

  @Override
  protected List<AttrValue<T, U>> getElement(long index) {
    List<AttrValue<T, U>> ret = new LinkedList<AttrValue<T, U>>();
    for (T key : this.attrsInReverseOrder) {
      List<AttrValue<T, U>> values = this.attrValues.get(key);
      int sz = values.size();
      int mod = (int) (index % sz);
      index /= sz;
      ret.add(values.get(mod));
    }
    Collections.reverse(ret);
    return ret;
  }

  private static <T, U> long calculateSize(List<AttrValue<T, U>> attributeValues) {
    long ret = 1;
    for (List<AttrValue<T, U>> values : attrValues(attributeValues).values()) {
      long sz = values.size();
      if (sz > Long.MAX_VALUE / ret) {
        throw new IllegalArgumentException(String.format("Overflow. Too many attributes or attribute values: %d * %d", ret, sz));
      } else {
        ret *= sz;
      }
    }
    return ret;
  }

  private static <T, U> Map<T, List<AttrValue<T, U>>> attrValues(List<AttrValue<T, U>> attributeValues) {
    Map<T, List<AttrValue<T, U>>> ret = new HashMap<T, List<AttrValue<T, U>>>();
    for (AttrValue<T, U> cur : attributeValues) {
      List<AttrValue<T, U>> values = ret.get(cur.attr());
      if (values == null) {
        values = new LinkedList<AttrValue<T, U>>();
        ret.put(cur.attr(), values);
      }
      values.add(cur);
    }
    return ret;
  }

  @Override
  public long indexOf(List<AttrValue<T, U>> element) {
    Utils.checkArgument(element.size() == attrValues.size(), "Invalid number of attributes; (expected=%d, actual=%d)", attrValues.size(), element.size());
    return calculateIndexOf(element);
  }

  @Override
  protected long calculateIndexOf(List<AttrValue<T, U>> element) {
    int c = 1;
    long ret = 0;
    for (int i = 0; i < element.size(); i++) {
      AttrValue<T, U> each = element.get(element.size() - i - 1);
      ret += c * attrValues.get(each.attr()).indexOf(each);
      c *= attrValues.get(each.attr()).size();
    }
    return ret;
  }

  public static void main(String... args) {
    List<AttrValue<String, String>> attrValues = new LinkedList<AttrValue<String, String>>();
    attrValues.add(attrValue("key1", "A"));
    attrValues.add(attrValue("key1", "B"));
    attrValues.add(attrValue("key2", "a"));
    attrValues.add(attrValue("key2", "b"));
    attrValues.add(attrValue("key3", "X"));
    attrValues.add(attrValue("key3", "Y"));

    Enumerator<AttrValue<String, String>> enumerator = new CartesianEnumerator<String, String>(attrValues);

    for (int i = 5; i < enumerator.size(); i++) {
      System.out.printf("%s: %s -> %s : %s%n", i, enumerator.get(i), enumerator.indexOf(enumerator.get(i)), enumerator.get(enumerator.indexOf(enumerator.get(i))));
    }
  }

  private static AttrValue<String, String> attrValue(String attr, String value) {
    return new AttrValue<String, String>(attr, value);
  }
}
