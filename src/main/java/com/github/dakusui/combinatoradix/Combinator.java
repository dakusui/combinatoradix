package com.github.dakusui.combinatoradix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinator<T> extends Enumerator.Base<T> {
  static class CDivResult {
    long mod;
    int  quotient;
  }


  public Combinator(List<? extends T> items, int k) {
    super(arrayList(items), k, calculateSize(items, k));
  }

  protected Combinator(List<? extends T> items, int k, long size) {
    super(arrayList(items), k, size);
  }

  @Override
  protected List<T> getElement(long index) {
    List<T> ret = new LinkedList<T>();
    List<T> tmp = new ArrayList<T>(this.symbols);
    int[] locator = index2locator(index, symbols.size(), k);
    for (int i : locator) {
      T last = null;
      for (int j = 0; j <= i; j++) {
        last = tmp.remove(0);
      }
      ret.add(last);
    }
    return ret;
  }

  /*
 * 0:[a, b]: [a,b,c,d];[];[a] -> [b,c,d];[a];[b] -> [c,d];[a,b]; 0
 * 1:[a, c]: [a,b,c,d];[];[a] -> [b,c,d];[a];[c] -> [d];[a,c]; 1
 * 2:[a, d]:
 * 3:[b, c]:
 * 4:[b, d]: [a,b,c,d];[];[b] -> [c,d];[b];[d] -> [c];[b,d];; 4
 * 5:[c, d]:
 */
  @Override
  protected long calculateIndexOf(List<T> element) {
    // 0:[a, b]:0
    // 1:[a, c]:1
    // 2:[a, d]:2
    // 3:[b, c]:3
    // 4:[b, d]:4
    // 5:[c, d]:6
    long ret = 0;
    int i = element.size() - 1;
    List<T> remainingSymbols = new LinkedList<T>(symbols);
    for (T each : element) {
      ret += numberOfSequencesConsumedBefore(remainingSymbols.indexOf(each), i, symbols.size());
      remainingSymbols = remainingSymbols.subList(remainingSymbols.indexOf(each) + 1, remainingSymbols.size());
      i--;
    }
    return ret;
  }

  private int numberOfSequencesConsumedBefore(int digit, int positionFromRightMost, int numSymbols) {
    //
    // [a, b, c, d]
    // c, 1, 4 -> f(2, 1, 4) -> 3 + 2 = 5 = 3C1 + 2C1
    // a, 1, 4 ->
    //
    if (positionFromRightMost == 0)
      return digit;
    int ret = 0;
    for (int i = digit; i > 0; i--) {
      ret += Utils.nCk(numSymbols - i, positionFromRightMost);
    }
    return ret;
  }

  private static <T> List<T> arrayList(List<T> items) {
    if (items instanceof ArrayList) {
      return items;
    }
    return new ArrayList<T>(items);
  }

  private static void cdiv(CDivResult result, long index, int n, int k) {
    int q = 0;
    for (int nn = n - 1; nn >= k - 1; nn--) {
      long nnCk_1 = Utils.nCk(nn, k - 1);
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
    for (int i = 0; i < k; i++) {
      cdiv(result, index, n, k - i);
      ret[i] = result.quotient;
      index = result.mod;
      n -= (result.quotient + 1);
    }
    return ret;
  }

  private static <T> long calculateSize(List<? extends T> items, int k) {
    return Utils.nCk(items.size(), k);
  }
}
