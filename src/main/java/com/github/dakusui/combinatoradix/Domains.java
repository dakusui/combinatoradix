package com.github.dakusui.combinatoradix;

import java.util.*;

public interface Domains<T, U> {
	List<T> getDomainNames();

	List<U> getDomain(T domainName);

	class DomainsImpl<T, U> extends LinkedHashMap<T, List<U>>
			implements Domains<T, U> {
		@Override public List<T> getDomainNames() {
			return new LinkedList<T>(this.keySet());
		}

		@Override public List<U> getDomain(T domainName) {
			return Collections.unmodifiableList(this.get(domainName));
		}
	}

	class Builder<T, U> {
		private final Map<T, List<U>> map;

		public Builder() {
			this(new LinkedHashMap<T, List<U>>());
		}

		public Builder(Map<T, List<U>> map) {
			this.map = map;
		}

		public Builder<T, U> addDomain(T key, U... values) {
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
