package com.github.dakusui.combinatoradix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.github.dakusui.combinatoradix.Utils.checkArgument;
import static java.util.Arrays.asList;

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

  @Override
  public long indexOf(List<E> entry) {
    int entrySize = entry.size();
    int expectedSize = sets.size();
    checkArgument(
        entry.size() == sets.size(),
        "Size of entry %d is not valid. It should have been %d",
        entrySize,
        expectedSize
    );
    for (int i = 0; i < entrySize; i++) {
      E value = entry.get(i);
      List<? extends E> validValues = sets.get(entrySize - i - 1);
      checkArgument(
          validValues.contains(value),
          "entry.get(%d)='%s' is not a valid value. Valid values: %s",
          i,
          value,
          validValues
      );
    }
    return calculateIndexOf(entry);
  }

  private long calculateIndexOf(List<E> entry) {
    long ret = 0;
    int c = 1;
    for (int i = 0; i < entry.size(); i++) {
      int j = entry.size() - i - 1;
      List<? extends E> set = sets.get(i);
      ret += set.indexOf(entry.get(j)) * c;
      c *= set.size();
    }
    return ret;
  }

  /*
   * sets:
   * 0| a b
   * 1| a b c
   * 2| a b c d
   *
   * index:
   * 13
   *
   * ret:
   * 0|
   * 1|
   * 2|
   */
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
  public java.util.Iterator<List<E>> iterator() {
    return new Enumerator.Iterator<E>(0,this);
  }

  public static void main(String... args) {
    //noinspection unchecked
    Cartesianator<String> cartesianator = new Cartesianator<String>(
        asList(
            asList("a", "b"),
            asList("a", "b", "c"),
            asList("a", "b", "c", "d")
        )
    );
    for (int i = 0; i < cartesianator.size; i++) {
      System.out.println(i + ":" + cartesianator.get(i) + ":" + cartesianator.indexOf(cartesianator.get(i)));
    }
  }
}
