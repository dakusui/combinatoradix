package com.github.dakusui.enumerator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.dakusui.enumerator.Combinator;
import com.github.dakusui.enumerator.Enumerator;

public class CombinatorTest extends EnumeratorTestBase {
	@Test
	public void test_nC0() {
		Enumerator<String> combinator = new Combinator<String>(testset1(), 0);
		assertEquals(true, combinator.hasNext());
		assertEquals(toList(), combinator.next());
		assertEquals(false, combinator.hasNext());
	}

	@Test
	public void test_nC1() {
		Enumerator<String> combinator = new Combinator<String>(testset1(), 1);
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
		Enumerator<String> combinator = new Combinator<String>(testset1(), 2);
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
		Enumerator<String> combinator = new Combinator<String>(testset1(), 3);
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
