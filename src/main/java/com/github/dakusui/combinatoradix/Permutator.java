package com.github.dakusui.combinatoradix;

import com.github.dakusui.combinatoradix.utils.InternalUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @param <T>
 */
public class Permutator<T> extends Enumerator.Base<T> {
  public Permutator(List<? extends T> items, int k) {
    this(items, k, calculateSize(items, k));
  }

  protected Permutator(List<? extends T> items, int k, long size) {
    super(items, k, size);
  }

  @Override
  protected List<T> getElement(long index) {
    int[] seq = index2locator(index, this.symbols.size() - k + 1, k);
    List<T> tmp = new LinkedList<T>(this.symbols);
    List<T> ret = new ArrayList<T>();
    for (int i : seq) {
      ret.add(tmp.remove(i));
    }
    return ret;
  }

  /*                 work
   * 0:[a, b, c] -> [a, b] -> [b] -> [];
   * 1:[a, c, b] -> [a, c] -> [a] -> [];
   * 2:[b, a, c] -> [b, a] -> [b] -> []; 0 ->
   * 3:[b, c, a]
   * 4:[c, a, b]
   * 5:[c, b, a]
   */
  @Override
  protected long calculateIndexOf(List<T> element) {
    long ret = 0;
    List<T> work = new ArrayList<T>(symbols);
    long c = 1;
    for (T each : element) {
      ret = c * ret + work.indexOf(each);
      work.remove(each);
      c = work.size();
    }
    return ret;
  }

  private static <T> long calculateSize(List<? extends T> items, int k) {
    return InternalUtils.nPk(items.size(), k);
  }

  private static int[] index2locator(long index, int lsradix, int k) {
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
}
