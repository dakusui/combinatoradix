package com.github.dakusui.combinatoradix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomogeniousCombinator<T> extends Combinator<T> {

	private static void hdiv(CDivResult result, long index, int n, int k) {
		int q = 0;
		for (int nn = n; nn > 0; nn--) {
			long nnHk_1 = nHk(nn, k-1); 
			if (index < nnHk_1) {
				result.mod = index;
				result.quotient = q;
				break;
			}
			index -= nnHk_1;
			q++;
		}
	}
	
	private static int[] index2locator(long index, int n, int k) {
		int[] ret = new int[k];
		CDivResult result = new CDivResult();
		for (int i = 0; i < k; i++) {
			hdiv(result, index, n, k - i);
			index = result.mod;
			ret[i] = result.quotient;
			n -= result.quotient;
		}
		return ret;
	}

	public HomogeniousCombinator(List<T> list, int k) {
		super(list, k);
	}

	@Override
	protected List<T> get_Protected(long index) {
		List<T> ret = new LinkedList<T>();
		List<T> tmp = new ArrayList<T>(this.items);
		int[] locator = index2locator(index, items.size(), k);
		for (int i : locator) {
			ret.add(tmp.get(i));
			tmp = tmp.subList(i, tmp.size());
		}
		return ret;
	}
	
	@Override
	public long size() {
		return nHk(this.items.size(), this.k);
	}
}
