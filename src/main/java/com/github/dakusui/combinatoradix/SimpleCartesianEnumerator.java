package com.github.dakusui.combinatoradix;

import java.util.LinkedHashMap;

public class SimpleCartesianEnumerator<K, V>
    extends CartesianEnumeratorAdaptor<LinkedHashMap<K, V>, K, V> {
  protected SimpleCartesianEnumerator(
      Domains<K, V> domains) {
    super(domains);
  }

  @Override protected LinkedHashMap<K, V> createMap() {
    return new LinkedHashMap<K, V>();
  }
}
