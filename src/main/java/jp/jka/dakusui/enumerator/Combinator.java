package jp.jka.dakusui.enumerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinator<T> extends Enumerator<T> {
	int k;

	protected Combinator(List<T> items, long maxIndex) {
		super(arrayList(items), maxIndex);
	}
	
	public Combinator(List<T> items, int k) {
		super(arrayList(items), maxIndex(items, k));
		this.k = k;
	}
	
	private static <T> List<T> arrayList(List<T> items) {
		if (items instanceof ArrayList) {
			return items;
		}
		return new ArrayList<T>(items);
	}

	@Override
	protected List<T> get_Protected(long index) {
		List<T> ret = new LinkedList<T>();
		List<T> tmp = new ArrayList<T>(this.list);
		int[] locator = index2locator(index, list.size(), k);
		for (int i : locator) {
			T last = null;
			for (int j = 0; j <= i; j++) {
				last = tmp.remove(0);
			}
			ret.add(last);
		}
		return ret;
	}

	static class CDivResult {
		long mod;
		int  quotient;
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

	static <T> long maxIndex(List<T> items, int k) {
		return nCk(items.size(), k);
	}
	
	static void cdiv(CDivResult result, long index, int n, int k) {
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
}
