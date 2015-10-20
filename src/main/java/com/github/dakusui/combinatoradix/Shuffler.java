package com.github.dakusui.combinatoradix;

import java.util.*;

public class Shuffler<E> extends Permutator<E> {
  private int[] index;

  protected Shuffler(List<? extends E> items, long size, long randomSeed) {
    super(items, items.size(), size);
    Random random = new Random(randomSeed);
    this.index = new int[(int) size];
    for (int i = 0; i < this.index.length; i++) {
      this.index[i] = random.nextInt(31) % (int)this.size();
    }
  }

  @Override
  protected List<E> getElement(long index) {
    return super.getElement(this.index[(int) index]);
  }
}
