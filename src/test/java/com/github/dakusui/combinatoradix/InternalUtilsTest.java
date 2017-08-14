package com.github.dakusui.combinatoradix;

import com.github.dakusui.combinatoradix.utils.InternalUtils;
import com.github.dakusui.combinatoradix.utils.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InternalUtilsTest extends TestBase {
  @Test
  public void givenInvalidArguments$when_nCk$then0() {
    assertEquals(0, InternalUtils.nCk(2, 3));
  }

  @Test
  public void givenInvalidArguments$when_nPk$then0() {
    assertEquals(0, InternalUtils.nPk(2, 3));
  }
}
