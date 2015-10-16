package com.github.dakusui.combinatoradix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinator<T> extends Enumerator<T> {
	static class CDivResult {
		long mod;
		int  quotient;
	}
	
	private static <T> List<T> arrayList(List<T> items) {
		if (items instanceof ArrayList) {
			return items;
		}
		return new ArrayList<T>(items);
	}
	
	private static void cdiv(CDivResult result, long index, int n, int k) {
		int q = 0;
		for (int nn = n-1; nn >= k-1; nn--) {
			long nnCk_1 = nCk(nn, k-1); 
			if (index < nnCk_1) {
				result.mod = index;
				result.quotient = q;
				break;
			}
			index -= nnCk_1;
			q++;
		}
	}

	private static int[] index2locator(long index, int n, int k) {
		int[] ret = new int[k];
		CDivResult result = new CDivResult();
		for (int i = 0; i < k; i ++) {
			cdiv(result, index, n, k-i);
			ret[i] = result.quotient;
			index = result.mod;
			n -= (result.quotient + 1);
		}
		return ret;
	}

	public Combinator(List<T> items, int k) {
		super(arrayList(items), k);
	}
	
	@Override
	protected List<T> get_Protected(long index) {
		List<T> ret = new LinkedList<T>();
		List<T> tmp = new ArrayList<T>(this.items);
		int[] locator = index2locator(index, items.size(), k);
		for (int i : locator) {
			T last = null;
			for (int j = 0; j <= i; j++) {
				last = tmp.remove(0);
			}
			ret.add(last);
		}
		return ret;
	}

	@Override
	public long size() {
		return nCk(this.items.size(), this.k);
	}
}
