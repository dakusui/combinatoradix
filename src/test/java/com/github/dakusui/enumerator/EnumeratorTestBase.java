package com.github.dakusui.enumerator;

import java.util.ArrayList;
import java.util.List;

public class EnumeratorTestBase {
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

	static <T> List<T> toList(T... args) {
		List<T> ret = new ArrayList<T>();
		for (T v : args) {
			ret.add(v);
		}
		return ret;
	}
}
