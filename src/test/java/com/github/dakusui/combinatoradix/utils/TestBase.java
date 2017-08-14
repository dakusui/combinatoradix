package com.github.dakusui.combinatoradix.utils;

import org.junit.After;
import org.junit.Before;

public class TestBase {
  @Before
  public void suppressStdOutErrIfRunUnderSurefire() {
    TestUtils.suppressStdOutErrIfRunUnderSurefire();
  }

  @After
  public void restoreStdOutErr() {
    TestUtils.restoreStdOutErr();
  }
}
