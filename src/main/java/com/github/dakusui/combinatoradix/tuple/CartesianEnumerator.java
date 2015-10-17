package com.github.dakusui.combinatoradix.tuple;

import com.github.dakusui.combinatoradix.Enumerator;

import java.util.*;

public class CartesianEnumerator<T, U> extends Enumerator<AttrValue<T, U>> {

  private final ArrayList<T> attrsInReverseOrder;
  private Map<T, List<AttrValue<T, U>>> attrValues = new HashMap<T, List<AttrValue<T, U>>>();

  @SuppressWarnings("unchecked")
  public CartesianEnumerator(List<AttrValue<T, U>> attributeValues) {
    super(attributeValues, countAttributes(attributeValues.toArray(new AttrValue[attributeValues.size()])));
    this.attrsInReverseOrder = new ArrayList<T>(this.k);
    for (AttrValue<T, U> cur : this.items) {
      if (!this.attrsInReverseOrder.contains(cur.attr())) {
        this.attrsInReverseOrder.add(cur.attr());
      }
    }
    this.attrValues = attrValues();
    Collections.reverse(this.attrsInReverseOrder);
  }

  private static int countAttributes(AttrValue<Object, Object>[] attributeValues) {
    Set<AttrValue<?, ?>> attrs = new HashSet<AttrValue<?, ?>>();
    Collections.addAll(attrs, attributeValues);
    return attrs.size();
  }

  @Override
  protected List<AttrValue<T, U>> get_Protected(long index) {
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

  @Override
  public long size() {
    long ret = 1;
    for (List<AttrValue<T, U>> values : attrValues().values()) {
      long sz = values.size();
      if (sz > Long.MAX_VALUE / ret) {
        throw new IllegalArgumentException(String.format("Overflow. Too many attributes or attribute values: %d * %d", ret, sz));
      } else {
        ret *= sz;
      }
    }
    return ret;
  }

  private Map<T, List<AttrValue<T, U>>> attrValues() {
    Map<T, List<AttrValue<T, U>>> ret = new HashMap<T, List<AttrValue<T, U>>>();
    for (AttrValue<T, U> cur : this.items) {
      List<AttrValue<T, U>> values = ret.get(cur.attr());
      if (values == null) {
        values = new LinkedList<AttrValue<T, U>>();
        ret.put(cur.attr(), values);
      }
      values.add(cur);
    }
    return ret;
  }
}