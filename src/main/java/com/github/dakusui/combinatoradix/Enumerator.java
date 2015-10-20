package com.github.dakusui.combinatoradix;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Enumerator<T> implements Iterable<List<T>> {
    public static long nCk(long n, long k) {
        long ret = 1;
        long j = 1;
        for (long i = n; i > n - k; i--) {
            if (i > Long.MAX_VALUE / ret) {
                throw new IllegalArgumentException(String.format("Overflow. Too big numbers are used %sC%s: %d * %d", n, k, ret, i));
            }
            ret *= i;
            ret /= j;
            j++;
        }
        return ret;
    }

    public static long nHk(int n, int k) {
        return nCk(n + k - 1, k);
    }

    public static long nPk(long n, long k) {
        long ret = 1;
        for (long i = n; i > n - k; i--) {
            if (i > Long.MAX_VALUE / ret) {
                throw new IllegalArgumentException(String.format("Overflow. Too big numbers are used %sP%s: %d * %d", n, k, ret, i));
            }
            ret *= i;
        }
        return ret;
    }

    private final long enumSize;

    protected final int k;

    protected final List<T> items;

    protected Enumerator(List<T> items, int k) {
        this.items = items;
        this.k = k;
        this.enumSize = size();
    }

    public List<T> get(long index) {
        if (index < enumSize) {
            return get_Protected(index);
        }
        String msg = String.format("Index (%d) must be less than %d", index, this.enumSize);
        throw new IndexOutOfBoundsException(msg);
    }

    protected abstract List<T> get_Protected(long index);

    public abstract long size();

    @Override
    public Iterator<List<T>> iterator() {
        return new Iterator<List<T>>() {
            private long index;

            @Override
            public boolean hasNext() {
                return this.index < Enumerator.this.enumSize;
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
                throw new UnsupportedOperationException("This operation is not supported.");
            }
        };
    }
}
