package com.github.dakusui.combinatoradix;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

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

  @Override
  protected long calculateIndexOf(List<T> element) {
    long ret = 0;
    List<T> work = new LinkedList<T>(items);
    Collections.reverse(work);
    long c = 1;
    //    for (int i = element.size() - 1; i >= 0; i--) {
    for (int i = 0; i < element.size(); i++) {
      T cur = element.get(i);
      ret += work.indexOf(cur) * c;
      c *= work.size();
      work.remove(cur);
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

  public static void main(String... args) {
    Permutator<String> permutator = new Permutator<String>(
        asList(
            "a", "b", "c"
        ),
        3
    );
    for (int i = 0; i < permutator.size(); i++) {
      System.out.printf("%d:%s:%d%n", i, permutator.getElement(i), permutator.indexOf(permutator.getElement(i)));
    }
  }
}
