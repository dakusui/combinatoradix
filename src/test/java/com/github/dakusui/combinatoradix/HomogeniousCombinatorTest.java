package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HomogeniousCombinatorTest extends EnumeratorTestBase {
	@Test
	public void test_empty() {
		// Empty set should result in empty iterator immediately
		Iterator<List<String>> i = new HomogeniousCombinator<String>(Collections.<String>emptyList(), 0).iterator();
		assertTrue(i.hasNext());
		assertEquals(Collections.emptyList(), i.next());
		assertFalse(i.hasNext());
	}

	@Test
	public void test_nH1() {
		Iterator<List<String>> homogeniousCombinator = new HomogeniousCombinator<String>(testset1(), 1).iterator();
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("A"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("B"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("C"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("D"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("E"), homogeniousCombinator.next());
		assertEquals(false, homogeniousCombinator.hasNext());
	}

	@Test
	public void test_nH2() {
		Iterator<List<String>> homogeniousCombinator = new HomogeniousCombinator<String>(testset1(), 2).iterator();
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("A", "A"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("A", "B"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("A", "C"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("A", "D"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("A", "E"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("B", "B"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("B", "C"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("B", "D"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("B", "E"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("C", "C"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("C", "D"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("C", "E"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("D", "D"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("D", "E"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(asList("E", "E"), homogeniousCombinator.next());
		assertEquals(false, homogeniousCombinator.hasNext());
	}

	public static void main(String[] args) {
		List<String> items = new LinkedList<String>();
		items.add("A");
		items.add("B");
		items.add("C");
		items.add("D");
		items.add("E");
		
		Iterator<List<String>> enumerator = new HomogeniousCombinator<String>(items, 2).iterator();
		int i = 0;
		while (enumerator.hasNext()) {
			System.out.println(i + ":" + enumerator.next());
			//enumerator.next();
			i++;
		}
	}
}
