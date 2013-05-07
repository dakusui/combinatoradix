package jp.jka.dakusui.enumerator;

import java.util.LinkedList;
import java.util.List;


public class Permutator<T> extends Enumerator<T> {
	List<T> list;
	int k;
	
	public Permutator(List<T> list, int k) {
		super(nPk(list.size(), k));
		this.list = list;
		this.k = k;
	}
		
	@Override
	public List<T> get(long index) {
		int[] seq = int2frint(index, this.list.size() - k + 1, k);
		List<T> tmp = new LinkedList<T>(this.list);
		List<T> ret = new LinkedList<T>();
		for (int i : seq) {
			ret.add(tmp.remove((int)i));
		}
		return ret;
	}

	static int[] int2frint(long index, int lsradix, int k) {
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
	
	public static void main(String... args) {
		List<String> work = new LinkedList<String>();
		work.add("A");
		work.add("B");
		work.add("C");
//		work.add("D");
//		work.add("E");
		
		Permutator<String> c = new Permutator<String>(work, 3);
		
		while (c.hasNext()) {
			System.out.println(c.next());
		}
	}
}
