package com.github.dakusui.combinatoradix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.github.dakusui.combinatoradix.Utils.nHk;

public class HomogeniousCombinator<T> extends Combinator<T> {

  public HomogeniousCombinator(List<? extends T> list, int k) {
    super(list, k, calculateSize(list, k));
  }

  @Override
  protected List<T> getElement(long index) {
    List<T> ret = new LinkedList<T>();
    List<T> tmp = new ArrayList<T>(this.symbols);
    int[] locator = index2locator(index, symbols.size(), k);
    for (int i : locator) {
      ret.add(tmp.get(i));
      tmp = tmp.subList(i, tmp.size());
    }
    return ret;
  }


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
      work = work.subList(ret[i], work.size());
    }
    return ret;
  }

  private static long numSeq(int[] digit, int numSymbols, int numChosen) {
    Utils.checkArgument(numChosen >= 0, "numChosen(%s) mustn't be negative", numChosen);
    Utils.checkArgument(numChosen <= numSymbols, "numChosen(%s) mustn't be greater than numSymbols(%s)", numChosen, numSymbols);
    Utils.checkArgument(digit != null, "digit mustn't be null");
    Utils.checkArgument(digit.length > 0 && digit.length <= numChosen, "digit(%s) less than or equal to %s", digit, numChosen);

    if (digit.length == numChosen) {
      if (digit.length == 1)
        return digit[digit.length - 1];
      return digit[digit.length - 1] + numSeq(Utils.chop(digit), numSymbols, numChosen);
    }
    if (digit.length == 1) {
      long ret = 0;
      for (int i = 0; i < digit[0]; i++) {
        ret += nHk(numSymbols - i, numChosen - 1);
      }
      return ret;
    }
    long ret = 0;
    int[] chopped = Utils.chop(digit);
    ret += numSeq(chopped, numSymbols, numChosen);

    int symbolsConsumed = Utils.sumAll(chopped);
    for (int i = 0; i < digit[digit.length - 1]; i++) {
      int n = numSymbols - symbolsConsumed - i;
      ret += nHk(n, numChosen - digit.length);
    }
    return ret;
  }

  private static <T> long calculateSize(List<? extends T> items, int k) {
    return nHk(items.size(), k);
  }

  private static void hdiv(CDivResult result, long index, int n, int k) {
    int q = 0;
    for (int nn = n; nn > 0; nn--) {
      long nnHk_1 = nHk(nn, k - 1);
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
}
