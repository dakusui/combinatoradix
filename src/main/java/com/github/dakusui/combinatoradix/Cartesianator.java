package com.github.dakusui.combinatoradix;

import java.util.*;

public class Cartesianator<E> implements Enumerator<E> {
  private final List<List<? extends E>> sets;
  private final long                    size;

  @Override
  public List<E> get(long index) {
    if (index < this.size) {
      return getElement(index);
    }
    String msg = String.format("Index (%d) must be less than %d", index, this.size);
    throw new IndexOutOfBoundsException(msg);
  }

  @Override
  public long size() {
    return this.size;
  }

  private List<E> getElement(long index) {
    List<E> ret = new LinkedList<E>();
    for (List<? extends E> eachSet : this.sets) {
      int sz = eachSet.size();
      int mod = (int) (index % sz);
      index /= sz;
      ret.add(eachSet.get(mod));
    }
    Collections.reverse(ret);
    return ret;
  }

  private static <U> long calculateSize(List<? extends List<? extends U>> sets) {
    long ret = 1;
    for (List<? extends U> eachSet : sets) {
      long sz = eachSet.size();
      if (sz > Long.MAX_VALUE / ret) {
        throw new IllegalArgumentException(String.format("Overflow. Too many attributes or attribute values: %d * %d", ret, sz));
      } else {
        ret *= sz;
      }
    }
    return ret;
  }

  protected Cartesianator(List<? extends List<? extends E>> sets) {
    this.sets = new ArrayList<List<? extends E>>(sets);
    Collections.reverse(this.sets);
    this.size = calculateSize(sets);
  }

  @Override
  public Iterator<List<E>> iterator() {
    return null;
  }
}
