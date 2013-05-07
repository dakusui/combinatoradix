package jp.jka.dakusui.enumerator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Enumerator<T> implements Iterator<List<T>>{

	private long index;
	private long numEnum;

	protected Enumerator(long numEnum) {
		this.numEnum = numEnum;
	}
	
	public abstract List<T> get(long index);
	
	static long nCk(long n, long k) {
		return nPk(n, k) / factorial(k);
	}

	static long nPk(long n, long k) {
		long ret = 1;
		for (long i = n; i > n-k; i-- ) {
			ret *= i;
		}
		return ret;
	}
	
	static long factorial(long n) {
		if (n < 0) {
			String msg = null;
			throw new RuntimeException(msg);
		}
		if (n == 0) return 1;
		long ret = 1;
		for (long i = n ; i > 1; i--) {
			ret *= i;
		}
		return ret;
	}
	
	@Override
	public boolean hasNext() {
		return this.index < this.numEnum;
	}

	@Override
	public List<T> next() {
		if (!hasNext()) {
			String message = "No more element in this enumberator.";
			throw new NoSuchElementException(message);
		}
		return get(this.index++);
	}

	@Override
	public void remove() {
		String message = "This operation is not supported.";
		throw new UnsupportedOperationException(message);
	}

}
