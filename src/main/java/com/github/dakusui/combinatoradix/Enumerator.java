package com.github.dakusui.combinatoradix;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public interface Enumerator<T> {
  List<T> get(long index);
  long size();

  abstract class Base<T> implements Enumerator<T>, Iterable<List<T>> {

    private final long enumSize;

    protected final int k;

    protected final List<? extends T> items;

    /**
     * Creates an object of this class.
     *
     * @param items A list of elements from which returned value of {@code get(int)} will be chosen.
     * @param k Number of elements chosen from {@code items}
     * @param size Number of lists this object can return.
     */
    protected Base(List<? extends T> items, int k, long size) {
      this.items = items;
      this.k = k;
      this.enumSize = size;
    }

    @Override
    public List<T> get(long index) {
      if (index < enumSize) {
        return getElement(index);
      }
      String msg = String.format("Index (%d) must be less than %d", index, this.enumSize);
      throw new IndexOutOfBoundsException(msg);
    }

    protected abstract List<T> getElement(long index);

    final public long size() {
      return this.enumSize;
    }

    @Override
    public Iterator<List<T>> iterator() {
      return new Iterator<List<T>>() {
        private long index;

        @Override
        public boolean hasNext() {
          return this.index < Base.this.enumSize;
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
}
