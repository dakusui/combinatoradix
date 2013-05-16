package jp.jka.dakusui.enumerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinator<T> extends Enumerator<T> {
	private int k;

	public Combinator(List<T> items, int k) {
		super(new ArrayList<T>(items), nCk(items.size(), k));
		this.k = k;
	}
	
	@Override
	public List<T> get(long index) {
		List<T> ret = new LinkedList<T>();
		List<T> tmp = new ArrayList<T>(this.list);
		int[] dfrlong = long2dfrlong(index, list.size(), k);
		for (int i : dfrlong) {
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
	
	static int[] long2dfrlong(long index, int n, int k) {
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

	static void cdiv(long index, int n, int k) {
		CDivResult result = new CDivResult();
		cdiv(result, index, n, k);
		System.out.println("q=(" + result.quotient + "),mod=(" + result.mod + ")");
	}
}
