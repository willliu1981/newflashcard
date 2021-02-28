package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Map<Integer, Integer> map=new HashMap<>();
		// map.put(1, 1);
		// map.put(1, 2);
		// System.out.println(map);
//			A a=new A();
//			a.a="a";
//			
//			A b=null;
//			set(b,a);
//			System.out.println(b.a);
		
		Test2.EE e=Test2.EE.E2;
		
		switch(e) {
		case E2:
		}

		List<A> list = new ArrayList<>();
		list.add(new A("a1"));
		list.add(new A("b2"));
		list.add(new A("a3"));

		List<A> newList =  list.stream().filter(x -> {
			return x.a.startsWith("a");
		}).collect(Collectors.toList());
		newList.forEach(System.out::print);
		
//		A[] as=(A[]) (list.stream().filter(x -> {
//			return x.a.startsWith("a");
//		}).toArray());
		
		
	}

	static void set(A s, A t) {
		s = t;
	}

	static class A {
		String a;

		public A(String a) {
			this.a = a;
		}
		
		public String toString() {
			return String.format("a:%s ", this.a);
		}
	}
}
