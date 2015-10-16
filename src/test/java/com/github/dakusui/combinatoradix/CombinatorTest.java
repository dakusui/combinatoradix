package com.github.dakusui.combinatoradix;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CombinatorTest extends EnumeratorTestBase {
	@Test
	public void test_nC0() {
		Iterator<List<String>> combinator = new Combinator<String>(testset1(), 0).iterator();
		assertEquals(true, combinator.hasNext());
		assertEquals(toList(), combinator.next());
		assertEquals(false, combinator.hasNext());
	}

	@Test
	public void test_nC1() {
		Iterator<List<String>> combinator = new Combinator<String>(testset1(), 1).iterator();
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("B"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("C"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("D"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("E"), combinator.next());
		assertEquals(false, combinator.hasNext());
	}

	@Test
	public void test_nC2() {
		Iterator<List<String>> combinator = new Combinator<String>(testset1(), 2).iterator();
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A", "B"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A", "C"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A", "D"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A", "E"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("B", "C"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("B", "D"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("B", "E"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("C", "D"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("C", "E"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("D", "E"), combinator.next());
		assertEquals(false, combinator.hasNext());
	}

	@Test
	public void test_nC3() {
		Iterator<List<String>> combinator = new Combinator<String>(testset1(), 3).iterator();
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A", "B", "C"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A", "B", "D"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A", "B", "E"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A", "C", "D"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A", "C", "E"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("A", "D", "E"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("B", "C", "D"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("B", "C", "E"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("B", "D", "E"), combinator.next());
		assertEquals(true, combinator.hasNext());
		assertEquals(toList("C", "D", "E"), combinator.next());
		assertEquals(false, combinator.hasNext());
	}
}
