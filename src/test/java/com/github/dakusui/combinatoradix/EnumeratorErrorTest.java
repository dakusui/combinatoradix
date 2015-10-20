package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class EnumeratorErrorTest {
	Enumerator<String> enumerator = new Permutator<String>(Collections.singletonList("Hello"), 1);

	@Test(expected = NoSuchElementException.class)
	public void goOverLimitByNextMethod() {
		Iterator<List<String>> i = enumerator.iterator();
		assertTrue(i.hasNext());
		assertEquals("Hello", i.next().get(0));
		assertFalse(i.hasNext());
		i.next();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void goOverLimitByGetMethod() {
		assertEquals(1, enumerator.size());
		enumerator.get(1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void tryToRemoveAndMakeSureExceptionIsThrown() {
		enumerator.iterator().remove();
	}
}
