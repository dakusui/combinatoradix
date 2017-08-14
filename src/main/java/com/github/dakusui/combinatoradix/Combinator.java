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
    int[] encoded = encode(element);
    for (int i = 0; i < encoded.length; i++) {
      if (i == encoded.length - 1)
        ret += encoded[i];
      else
        ret += numberOfSequencesConsumedBefore(
            encoded[i],
            encoded.length - i - 1,
            symbols.size(),
            k
        );
    }
    return ret;
  }

  // symbols [s0,s1,s2,...,sn]
  //   |<---------k-------->|
  //         j                              j = k - i - 1
  //   |<---->
  //         <----i+1---->                d-1 >= i
  //         [a,...,.,.]   - numSymbols - 1 C i
  //         [b,...,.,.]   - numSymbols - 2 C i
  //         [c,...,.,.]   - numSymbols - d-1 C i
  //         [d=xi,...,x1,x0]; d = digit, i = positionFromRight
  //
  //     if numSymbols - j < i ?
  //
  //    ...  [a,...,.,.]
  //    ...  [b,...,.,.]
  //    ...  [c,...,.,.]
  //    ...  [d=xi,...,x1,x0]; d = digit, i = positionFromRight

  private static int numberOfSequencesConsumedBefore(int digit, int positionFromRightMost, int numSymbols, int numChosen) {
    //
    // [a, b, c, d]
    // c, 1, 4 -> f(2, 1, 4) -> 3 + 2 = 5 = 3C1 + 2C1
    // a, 1, 4 ->

    //    [0, 1, -] ...?
    //
    if (positionFromRightMost == 0)
      throw new IllegalArgumentException();
    int ret = 0;
    for (int i = 0; i < digit; i++) {
      int j = numChosen - i - 1;
      //      if (numSymbols - j < i)
      ret += Utils.nCk(
          //          min(
          //numSymbols - j,
          numSymbols - i - 1,
          //              numChosen - i - 1
          //          ),
          positionFromRightMost
      ) - Utils.nCk(
          numSymbols - j -1,
          positionFromRightMost
      );
      //        ret += Utils.nCk(numSymbols - i - 1, numSymbols - j);
      //      else
      //      ret += Utils.nCk(numSymbols - i - 1, positionFromRightMost);
      //      ret += Utils.nCk(j - i - 1, positionFromRightMost);
    }
    return ret;
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
