package com.github.dakusui.combinatoradix;

import com.github.dakusui.combinatoradix.utils.InternalUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.github.dakusui.combinatoradix.utils.InternalUtils.nCk;

public class Combinator<T> extends Enumerator.Base<T> {
  static class CDivResult {
    long mod;
    int  quotient;
  }

  public Combinator(List<? extends T> items, int k) {
    super(InternalUtils.arrayList(items), k, calculateSize(items, k));
  }

  protected Combinator(List<? extends T> items, int k, long size) {
    super(InternalUtils.arrayList(items), k, size);
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
    //    long ret = 0;
    return numSeq(
        encode(element),
        symbols.size(),
        k
    );
  }

  int[] encode(List<T> element) {
    List<T> work = new LinkedList<T>(this.symbols);
    int[] ret = new int[this.k];
    for (int i = 0; i < ret.length; i++) {
      ret[i] = work.indexOf(element.get(i));
      work = work.subList(ret[i] + 1, work.size());
    }
    return ret;
  }

  private static long numSeq(int[] digit, int numSymbols, int numChosen) {
    InternalUtils.checkCondition(numChosen >= 0, "numChosen(%s) mustn't be negative", numChosen);
    InternalUtils.checkCondition(numChosen <= numSymbols, "numChosen(%s) mustn't be greater than numSymbols(%s)", numChosen, numSymbols);
    InternalUtils.checkCondition(digit != null, "digit mustn't be null");
    InternalUtils.checkCondition(digit.length > 0 && digit.length <= numChosen, "digit(%s) less than or equal to %s", digit, numChosen);

    if (digit.length == numChosen) {
      if (digit.length == 1)
        return digit[digit.length - 1];
      return digit[digit.length - 1] + numSeq(InternalUtils.chop(digit), numSymbols, numChosen);
    }
    if (digit.length == 1) {
      // [c] (2,-,-), [a,b,c,d]
      //    [a,b,c]
      //    [a,b,d]
      //    [a,b,e]
      //    [a,c,d]
      //    [a,c,e]
      //    [a,d,e] 4C2
      //    [b,c,d]
      //    [b,c,e]
      //    [b,d,e] 3C2
      long ret = 0;
      for (int i = 0; i < digit[0]; i++) {
        ret += nCk(numSymbols - i - 1, numChosen - 1);
      }
      return ret;
    }
    // [b,d] (1,1,-), [a,b,c,d]
    //    [a,b,c]
    //    [a,b,d]
    //    [a,b,e]
    //    [a,c,d]
    //    [a,c,e]
    //    [a,d,e] 4C2
    //    [b,c,d]
    //    [b,c,e]
    //
    long ret = 0;
    int[] chopped = InternalUtils.chop(digit);
    ret += numSeq(chopped, numSymbols, numChosen);

    int symbolsConsumed = InternalUtils.sumAll(chopped) + chopped.length + 1;
    for (int i = 0; i < digit[digit.length - 1]; i++) {
      int n = numSymbols - symbolsConsumed - i;
      ret += nCk(n, numChosen - digit.length);
    }
    return ret;
  }

  private static void cdiv(CDivResult result, long index, int n, int k) {
    int q = 0;
    for (int nn = n - 1; nn >= k - 1; nn--) {
      long nnCk_1 = nCk(nn, k - 1);
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
    return nCk(items.size(), k);
  }
}
