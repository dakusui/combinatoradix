package com.github.dakusui.enumerator;

import com.github.dakusui.enumerator.tuple.AttrValue;
import com.github.dakusui.enumerator.tuple.CartesianEnumerator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class CartesianEnumeratorAdaptor<T extends Map<U, V>, U, V>
    implements Iterator<T>, Iterable<T> {
  private final CartesianEnumerator<U, V> cart;

  protected CartesianEnumeratorAdaptor(Domains<U, V> domains) {
    this.cart = new CartesianEnumerator<U, V>(domains2AttrValues(domains));
  }

  @Override public Iterator<T> iterator() {
    return this;
  }

  @Override public boolean hasNext() {
    return this.cart.hasNext();
  }

  @Override public T next() {
    return attrValues2map(this.cart.next());
  }

  @Override public void remove() {
    throw new UnsupportedOperationException();
  }

  public Map<U, V> get(long index) {
    return attrValues2map(this.cart.get(index));
  }

  public long size() {
    return cart.size();
  }

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

}
