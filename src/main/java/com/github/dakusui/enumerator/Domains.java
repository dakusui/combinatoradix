package com.github.dakusui.enumerator;

import java.util.*;

public interface Domains<T, U> {
  public List<T> getDomainNames();

  public List<U> getDomain(T domainName);

  static class DomainsImpl<T, U> extends LinkedHashMap<T, List<U>>
      implements Domains<T, U> {
    @Override public List<T> getDomainNames() {
      return new LinkedList<T>(this.keySet());
    }

    @Override public List<U> getDomain(T domainName) {
      return Collections.unmodifiableList(this.get(domainName));
    }
  }

  public static class Builder<T, U> {
    private LinkedHashMap<T, List<U>> map = new LinkedHashMap<T, List<U>>();

    public Builder addDomain(T key, U... values) {
      this.map.put(key, Arrays.asList(values));
      return this;
    }

    public Domains<T, U> build() {
      DomainsImpl<T, U> ret = new DomainsImpl<T, U>();
      for (T k : this.map.keySet()) {
        ret.put(k, this.map.get(k));
      }
      return ret;
    }
  }
}
