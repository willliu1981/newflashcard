package com.test2;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Test9 {

	public static void main(String[] args) {
		
		Map<String, String> rule = new LinkedHashMap<>();
		rule.put("[\\s]+", "_");
		rule.put("[_]+", "-");
		rule.put("[-]", "_");

		String str="a b";
		
		
		Iterator<Entry<String, String>> it = rule.entrySet().iterator();
		Entry<String, String> s=it.next();
		
		System.out.println(s);
	}

}
