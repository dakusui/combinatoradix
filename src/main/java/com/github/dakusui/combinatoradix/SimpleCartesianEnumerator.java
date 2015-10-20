package com.github.dakusui.combinatoradix;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleCartesianEnumerator<K, V>
    extends CartesianEnumeratorAdaptor<Map<K, V>, K, V> {
  protected SimpleCartesianEnumerator(
      Domains<K, V> domains) {
    super(domains);
  }

  @Override protected Map<K, V> createMap() {
    return new LinkedHashMap<K, V>();
  }
}
