package com.github.dakusui.combinatoradix;

import com.github.dakusui.combinatoradix.utils.TestBase;

import java.util.ArrayList;
import java.util.List;

class EnumeratorTestBase extends TestBase {
  static List<String> testset1() {
    List<String> ret = new ArrayList<String>();
    ret.add("A");
    ret.add("B");
    ret.add("C");
    ret.add("D");
    ret.add("E");
    return ret;
  }

  static List<String> testset2() {
    List<String> ret = new ArrayList<String>();
    ret.add("A");
    ret.add("B");
    ret.add("C");
    return ret;
  }
}
