package com.test;

import java.util.HashMap;
import java.util.Map;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Map<Integer, Integer> map=new HashMap<>();
//		map.put(1, 1);
//		map.put(1, 2);
//		System.out.println(map);
		A a=new A();
		a.a="a";
		
		A b=null;
		set(b,a);
		System.out.println(b.a);
	}
	
	static void set(A s,A t) {
		s=t;
	}

	static class A{
		String a;
	}
}
