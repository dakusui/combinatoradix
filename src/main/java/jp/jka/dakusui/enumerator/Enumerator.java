package jp.jka.dakusui.enumerator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Enumerator<T> implements Iterator<List<T>>{

	static long factorial(long n) {
		if (n < 0) {
			String msg = "n cannot be less than 0. (" + n + ")";
			throw new RuntimeException(msg);
		}
		if (n == 0) return 1;
		long ret = 1;
		for (long i = n ; i > 1; i--) {
			ret *= i;
		}
		return ret;
	}
	
	static long nCk(long n, long k) {
		return nPk(n, k) / factorial(k);
	}
	
	static long nHk(int n, int k) {
		long ret = nCk(n+k-1, k);
		return ret;
	}

	static long nPk(long n, long k) {
		long ret = 1;
		for (long i = n; i > n-k; i-- ) {
			ret *= i;
		}
		return ret;
	}
	
	private long index;

	protected List<T> list;
	
	private long maxIndex;

	protected Enumerator(List<T> list, long maxIndex) {
		this.maxIndex = maxIndex;
		this.list = list;
	}
	
	protected abstract List<T> get_Protected(long index);
	
	@Override
	public boolean hasNext() {
		return this.index < this.maxIndex;
	}

	@Override
	public List<T> next() {
		if (!hasNext()) {
			String message = "No more element in this enumberator.";
			throw new NoSuchElementException(message);
		}
		return get_Protected(this.index++);
	}

	@Override
	public void remove() {
		String message = "This operation is not supported.";
		throw new UnsupportedOperationException(message);
	}

	public List<T> get(long index) {
		if (index < maxIndex) {
			return get_Protected(index);
		}
		String msg = String.format("Index (%d) must be less than %d", index, this.maxIndex);
		throw new IndexOutOfBoundsException(msg);
	}
}
