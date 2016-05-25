package com.github.dakusui.combinatoradix;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ShufflerTest {

  @Test
  public void checkStability() {
    Enumerator<String> shuffler = Enumerators.shuffler(new LinkedList<String>(Arrays.asList("1", "2", "3", "4", "5")), 20, 4649);
    assertEquals(20, shuffler.size());
    String[] expectations = new String[] {
        "[3, 2, 4, 1, 5]", "[4, 1, 3, 2, 5]", "[4, 3, 2, 1, 5]", "[3, 1, 2, 4, 5]",
        "[4, 5, 1, 3, 2]", "[3, 5, 2, 1, 4]", "[5, 3, 4, 2, 1]", "[3, 1, 4, 5, 2]",
        "[5, 4, 1, 2, 3]", "[1, 2, 5, 4, 3]", "[1, 5, 3, 2, 4]", "[1, 4, 5, 3, 2]",
        "[5, 2, 1, 3, 4]", "[5, 3, 1, 4, 2]", "[5, 1, 4, 2, 3]", "[5, 1, 2, 4, 3]",
        "[5, 1, 3, 4, 2]", "[3, 4, 2, 1, 5]", "[3, 5, 2, 4, 1]", "[2, 5, 1, 3, 4]"
    };
    for (int i = 0; i < expectations.length; i++) {
      assertThat(String.format("Error found in %sth element", i), shuffler.get(i).toString(), CoreMatchers.equalTo(expectations[i]));
    }
  }

  @Test
  public void checkEvenness() {
    final int w = 2000;
    Enumerator<String> shuffler = Enumerators.shuffler(new LinkedList<String>(Arrays.asList("1", "2", "3", "4", "5")), w, 4649);
    assertEquals(w, shuffler.size());
    for (int i = 0; i < 5; i++) {
      for (int j = 1; j <= 5; j++) {
        assertThat(count(shuffler, Integer.toString(j), i), inRangeMatcher(w));
      }
    }
  }

  private BaseMatcher<Integer> inRangeMatcher(final int w) {
    return new BaseMatcher<Integer>() {
      int m = w / 5;
      int d = m / 10;
      int min = m - d;
      int max = m + d;

      @Override
      public boolean matches(Object item) {
        if (!(item instanceof Integer))
          return false;
        int v = (Integer) item;
        return min <= v && v <= max;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText(String.format("out of acceptable range (%s <= x <= %s). ", min, max));
      }
    };
  }

  private static int count(Enumerator<String> in, String target, int index) {
    int ret = 0;
    for (List<String> each : in) {
      if (target.equals(each.get(index))) {
        ret++;
      }
    }
    return ret;
  }

}
