package com.github.dakusui.enumerator;

import java.util.LinkedHashMap;

public class SimpleCartesianEnumerator<K, V>
    extends CartesianEnumeratorAdaptor<LinkedHashMap<K, V>, K, V> {
  protected SimpleCartesianEnumerator(
      Domains domains) {
    super(domains);
  }

  @Override protected LinkedHashMap<K, V> createMap() {
    return new LinkedHashMap<K, V>();
  }
}
