package com.github.dakusui.combinatoradix;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Enumerator<T> implements Iterable<List<T>> {

  private final long enumSize;

  protected final int k;

  protected final List<? extends T> items;

  protected Enumerator(List<? extends T> items, int k) {
    this.items = items;
    this.k = k;
    this.enumSize = size();
  }

  public List<T> get(long index) {
    if (index < enumSize) {
      return getElement(index);
    }
    String msg = String.format("Index (%d) must be less than %d", index, this.enumSize);
    throw new IndexOutOfBoundsException(msg);
  }

  protected abstract List<T> getElement(long index);

  public abstract long size();

  @Override
  public Iterator<List<T>> iterator() {
    return new Iterator<List<T>>() {
      private long index;

      @Override
      public boolean hasNext() {
        return this.index < Enumerator.this.enumSize;
      }

      @Override
      public List<T> next() {
        if (!hasNext()) {
          String message = "No more element in this enumberator.";
          throw new NoSuchElementException(message);
        }
        return getElement(this.index++);
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("This operation is not supported.");
      }
    };
  }
}
