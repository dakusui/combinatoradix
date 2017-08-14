package com.github.dakusui.combinatoradix;

import com.github.dakusui.combinatoradix.utils.InternalUtils;

import java.util.List;
import java.util.Random;

public class Shuffler<E> extends Permutator<E> {

  private final Random random;
  private final long   numPossibleSeqs;

  protected Shuffler(List<? extends E> items, long size, Random random) {
    super(items, items.size(), size);
    this.random = random;
    this.numPossibleSeqs = InternalUtils.nPk(items.size(), items.size());
  }

  @Override
  protected List<E> getElement(long index) {
    ////
    // In theory random.nextLong can return Long.MIN_VALUE. And Math.abs(Long.MIN_VALUE)
    // returns Long.MIN_VALUE. (This is not a typo. Refer to API reference.)
    long nextLong;
    //noinspection StatementWithEmptyBody
    for (nextLong = Long.MIN_VALUE; nextLong == Long.MIN_VALUE; nextLong = this.random.nextLong()) {
    }
    return super.getElement(Math.abs(nextLong) % numPossibleSeqs);
  }
}
