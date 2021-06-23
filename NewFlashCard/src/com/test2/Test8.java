package com.test2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Test8 {

	static class Book {
		String name;
		int price;

		Book() {

		}

//		Book(String name){
//			this.name=name;
//		}
	}

	static class EBook extends Book {
		EBook(int price) {
			this.price = price;
		}
	}

	public static void main(String[] args) {

		Map<String, String> rule = new  LinkedHashMap<>();
		rule.put("A", "a");
		rule.put("D", "d");
		rule.put("B", "b");
		rule.put("E", "e");
		rule.put("C", "c");
		rule.put("F", "f");

		rule.entrySet();
		rule.forEach((x1, x2) -> System.out.println(String.format("k= %s , v= %s", x1, x2)));

		System.out.println("***");

		Iterator<String> it = rule.keySet().iterator();

		while (it.hasNext()) {
			String ob = it.next();
			System.out.println(String.format("k= %s , v= %s", ob,rule.get(ob)));
		}

		System.out.println();
	}

}
