package com.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test3 {

	public static void main(String[] args) {

		List<String> ls = new ArrayList<>();
		ls.add("abdddd");
		ls.add("beee");
		ls.add("ebrrd");
		ls.add("bbcc");
		ls.add("Abc");
		ls.add("Xxx");
		ls.add("cbd");
		List<String> tails = new ArrayList<>();
		List<String> heads = new ArrayList<>();
		List<String> total = new ArrayList<>();

		String s = "b";

		heads = ls.stream().sorted().filter(x -> {
			boolean r = false;
			if (x.startsWith(s)) {
				tails.add(x);
			}else {
				r=true;
			}
			return r;
		}).collect(Collectors.toList());

		total.addAll(heads);
		total.addAll(tails);
		System.out.println("ls " + ls);
		System.out.println("heads " + heads);
		System.out.println("tatils " + tails);
		System.out.println("total " + total);
	}

}
