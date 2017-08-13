package com.github.dakusui.combinatoradix;

import java.util.List;
import java.util.NoSuchElementException;

public interface Enumerator<T> extends Iterable<List<T>> {
  List<T> get(long index);

  long size();

  long indexOf(List<T> entry);

  class Iterator<T> implements java.util.Iterator<List<T>> {
    private final Enumerator<T> enumerator;
    private       long          index;

    @SuppressWarnings("WeakerAccess")
    public Iterator(long offset, Enumerator<T> enumerator) {
      this.enumerator = enumerator;
      this.index = offset;
    }

    @Override
    public boolean hasNext() {
      return this.index < enumerator.size();
    }

    @Override
    public List<T> next() {
      if (!hasNext()) {
        String message = "No more element in this enumberator.";
        throw new NoSuchElementException(message);
      }
      return enumerator.get(this.index++);
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("This operation is not supported.");
    }
  }

  abstract class Base<T> implements Enumerator<T>, Iterable<List<T>> {

    private final long enumSize;

    protected final int k;

    protected final List<? extends T> symbols;

    /**
     * Creates an object of this class.
     *
     * @param symbols A list of elements from which returned value of {@code get(int)} will be chosen.
     * @param k     Number of elements chosen from {@code items}
     * @param size  Number of lists this object can return.
     */
    protected Base(List<? extends T> symbols, int k, long size) {
      this.symbols = symbols;
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

    @Override
    public long indexOf(List<T> element) {
      Utils.checkArgument(
          element.size() == k,
          "Size of element:%d is not valid (expected=%d)",
          element.size(),
          k
      );
      Utils.checkArgument(
          symbols.containsAll(element),
          "Element %s contained invalid value(s): (valid values=%s)",
          element,
          symbols
      );
      return calculateIndexOf(element);
    }

    abstract protected long calculateIndexOf(List<T> element);


    final public long size() {
      return this.enumSize;
    }

    @Override
    public java.util.Iterator<List<T>> iterator() {
      return new Iterator<T>(0, this);
    }
  }
}
