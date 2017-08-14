package com.github.dakusui.combinatoradix;

import com.github.dakusui.combinatoradix.tuple.AttrValue;
import com.github.dakusui.combinatoradix.tuple.CartesianEnumerator;

import java.util.*;

/**
 * User must guarantee that T is stable in order to use 'indexOf' method.
 *
 * @param <T> Type of map whose key is {@code U} and value is {@code V}.
 * @param <U> Type of keys in map.
 * @param <V> Type of values in map.
 */
public abstract class CartesianEnumeratorAdaptor<T extends Map<U, V>, U, V>
    implements Iterable<T> {
  private final CartesianEnumerator<U, V> cart;

  protected CartesianEnumeratorAdaptor(Domains<U, V> domains) {
    this.cart = new CartesianEnumerator<U, V>(domains2AttrValues(domains));
  }

  @Override
  public Iterator<T> iterator() {
    final Iterator<List<AttrValue<U, V>>> i = this.cart.iterator();
    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        return i.hasNext();
      }

      @Override
      public T next() {
        return attrValues2map(i.next());
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("This operation is not supported.");
      }
    };
  }

  public T get(long index) {
    return attrValues2map(this.cart.get(index));
  }

  public long indexOf(T entry) {
    return cart.indexOf(map2AttrValues(entry));
  }

  public long size() {
    return cart.size();
  }

  /**
   * A map returned by {@code get(long)} is created by this method.
   * A user can specify concrete {@code Map} class like {@code HashMap},
   * {@code TreeMap}, etc, by overriding this method.
   */
  abstract protected T createMap();

  List<AttrValue<U, V>> domains2AttrValues(Domains<U, V> domains) {
    List<AttrValue<U, V>> ret = new LinkedList<AttrValue<U, V>>();
    for (U domainName : domains.getDomainNames()) {
      for (V domainValue : domains.getDomain(domainName)) {
        ret.add(new AttrValue<U, V>(domainName, domainValue));
      }
    }
    return ret;
  }

  T attrValues2map(List<AttrValue<U, V>> attrValues) {
    T ret = createMap();
    for (AttrValue<U, V> attrValue : attrValues) {
      ret.put(attrValue.attr(), attrValue.value());
    }
    return ret;
  }

  List<AttrValue<U, V>> map2AttrValues(T map) {
    List<AttrValue<U, V>> ret = new ArrayList<AttrValue<U, V>>(map.size());
    for (Map.Entry<U, V> each : map.entrySet()) {
      ret.add(new AttrValue<U, V>(each.getKey(), each.getValue()));
    }
    return ret;
  }
}
