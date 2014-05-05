package com.github.dakusui.enumerator.tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.dakusui.enumerator.Enumerator;

public class CartesianEnumerator<T extends Object, U extends Object> extends Enumerator<AttrValue<T, U>> {

  private final ArrayList<T> attrsInReverseOrder;
  private Map<T, List<AttrValue<T, U>>> attrValues = new HashMap<T, List<AttrValue<T, U>>>();

  @SuppressWarnings("unchecked")
  public CartesianEnumerator(List<AttrValue<T, U>> attributeValues) {
    super(attributeValues, countAttributes(attributeValues.toArray(new AttrValue[] {})));
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
    for (AttrValue<?, ?> attrValue : attributeValues) {
      attrs.add(attrValue);
    }
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
      ret *= values.size();
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
