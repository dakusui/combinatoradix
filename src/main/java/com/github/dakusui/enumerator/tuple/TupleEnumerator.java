package com.github.dakusui.enumerator.tuple;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedMap;

/**
 * A base class for enumerators which choose one value for each attribute each
 * time.
 * 
 * @author hiroshi
 * 
 */
public abstract class TupleEnumerator<T, U> implements Iterator<Tuple<T, U>>, Iterable<Tuple<T, U>> {
  protected final SortedMap<T, List<U>> attrValues;
  private final long enumSize;
  private long index;

  protected TupleEnumerator(SortedMap<T, List<U>> attrValues) {
    this.attrValues = attrValues;
    this.enumSize = this.size();
    this.index = 0;
  }

  @Override
  public boolean hasNext() {
    return this.index < this.enumSize;
  }

  public Tuple<T, U> get(long index) {
    if (index < enumSize) {
      return get_Protected(index);
    }
    String msg = String.format("Index (%d) must be less than %d", index, this.enumSize);
    throw new IndexOutOfBoundsException(msg);
  }

  public abstract long size();

  @Override
  public Tuple<T, U> next() {
    if (!hasNext()) {
      String message = "No more element in this enumberator.";
      throw new NoSuchElementException(message);
    }
    return get_Protected(this.index++);
  }

  protected abstract Tuple<T, U> get_Protected(long l);

  @Override
  public void remove() {
    String message = "This operation is not supported.";
    throw new UnsupportedOperationException(message);
  }

  @Override
  public Iterator<Tuple<T, U>> iterator() {
    return this;
  }
}
