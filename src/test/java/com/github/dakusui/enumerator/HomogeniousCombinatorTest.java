package com.github.dakusui.enumerator;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.github.dakusui.enumerator.Enumerator;
import com.github.dakusui.enumerator.HomogeniousCombinator;

public class HomogeniousCombinatorTest extends EnumeratorTestBase {
	@Test
	public void test_nH1() {
		Enumerator<String> homogeniousCombinator = new HomogeniousCombinator<String>(testset1(), 1);
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("A"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("B"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("C"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("D"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("E"), homogeniousCombinator.next());
		assertEquals(false, homogeniousCombinator.hasNext());
	}

	@Test
	public void test_nH2() {
		Enumerator<String> homogeniousCombinator = new HomogeniousCombinator<String>(testset1(), 2);
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("A", "A"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("A", "B"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("A", "C"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("A", "D"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("A", "E"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("B", "B"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("B", "C"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("B", "D"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("B", "E"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("C", "C"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("C", "D"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("C", "E"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("D", "D"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("D", "E"), homogeniousCombinator.next());
		assertEquals(true, homogeniousCombinator.hasNext());
		assertEquals(toList("E", "E"), homogeniousCombinator.next());
		assertEquals(false, homogeniousCombinator.hasNext());
	}

	public static void main(String[] args) {
		List<String> items = new LinkedList<String>();
		items.add("A");
		items.add("B");
		items.add("C");
		items.add("D");
		items.add("E");
		
		Enumerator<String> enumerator = new HomogeniousCombinator<String>(items, 2);
		int i = 0;
		while (enumerator.hasNext()) {
			System.out.println(i + ":" + enumerator.next());
			//enumerator.next();
			i++;
		}
	}
}
