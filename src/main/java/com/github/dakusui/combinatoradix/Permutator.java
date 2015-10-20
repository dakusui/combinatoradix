package com.github.dakusui.combinatoradix;

import java.util.LinkedList;
import java.util.List;

/**
 * @param <T>
 */
public class Permutator<T> extends Enumerator.Base<T> {
  static int[] index2locator(long index, int lsradix, int k) {
    int[] seq = new int[k];
    long c = index;
    int radix = lsradix;
    for (int i = seq.length - 1; i >= 0; i--) {
      seq[i] = (int) (c % radix);
      c /= radix;
      radix++;
    }
    return seq;
  }

  @Override
  protected List<T> getElement(long index) {
    int[] seq = index2locator(index, this.items.size() - k + 1, k);
    List<T> tmp = new LinkedList<T>(this.items);
    List<T> ret = new LinkedList<T>();
    for (int i : seq) {
      ret.add(tmp.remove(i));
    }
    return ret;
  }

  private static <T> long calculateSize(List<? extends T> items, int k) {
    return Utils.nPk(items.size(), k);
  }

  public Permutator(List<? extends T> items, int k) {
    this(items, k, calculateSize(items, k));
  }

  protected Permutator(List<? extends T> items, int k, long size) {
    super(items, k, size);
  }
}
