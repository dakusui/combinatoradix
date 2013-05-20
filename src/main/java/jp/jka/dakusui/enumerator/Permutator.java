package jp.jka.dakusui.enumerator;

import java.util.LinkedList;
import java.util.List;


public class Permutator<T> extends Enumerator<T> {
	static int[] index2locator(long index, int lsradix, int k) {
		int[] seq = new int[k];
		long c = index;
		int radix = lsradix;
		for (int i = seq.length - 1; i >= 0; i--) {
			seq[i] = (int) (c % radix);
			c /= radix;
			radix++;
		}
		return seq;
	}
	
	public Permutator(List<T> items, int k) {
		super(items, k);
	}

	@Override
	protected List<T> get_Protected(long index) {
		int[] seq = index2locator(index, this.items.size() - k + 1, k);
		List<T> tmp = new LinkedList<T>(this.items);
		List<T> ret = new LinkedList<T>();
		for (int i : seq) {
			ret.add(tmp.remove((int)i));
		}
		return ret;
	}

	@Override
	protected long size() {
		return nPk(this.items.size(), this.k);
	}
}
