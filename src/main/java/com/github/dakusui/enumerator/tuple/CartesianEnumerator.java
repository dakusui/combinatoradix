package com.github.dakusui.enumerator.tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

public class CartesianEnumerator<T, U> extends TupleEnumerator<T, U> {

  public CartesianEnumerator(SortedMap<T, List<U>> attributeValues) {
    super(attributeValues);
  }

  @Override
  protected Tuple<T, U> get_Protected(long index) {
    Tuple<T, U> ret = new Tuple<T, U>();
    for (T key : keyListInReverseOrder()) {
      List<U> values = this.attrValues.get(key);
      int sz = values.size();
      int mod = (int) (index % sz);
      index /= sz;
      ret.put(key, values.get(mod));
    }
    return ret;
  }

  private List<T> keyListInReverseOrder() {
    List<T> ret = new ArrayList<T>(this.attrValues.size());
    for (T key : this.attrValues.keySet()) {
      ret.add(key);
    }
    Collections.reverse(ret);
    return ret;
  }

  @Override
  public long size() {
    long ret = 1;
    for (List<U> values : attrValues.values()) {
      ret *= values.size();
    }
    return ret;
  }
}
