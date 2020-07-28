package com.github.dakusui.combinatoradix.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
  @BeforeEach
  public void suppressStdOutErrIfRunUnderSurefire() {
    TestUtils.suppressStdOutErrIfRunUnderSurefire();
  }

  @AfterEach
  public void restoreStdOutErr() {
    TestUtils.restoreStdOutErr();
  }
}
