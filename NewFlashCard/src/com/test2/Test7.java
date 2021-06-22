package com.test2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test7 {
	
	private static final List<String> list;
	
	static {
		list=setList();
	}

	public static void main(String[] args) throws IOException {

		System.out.println(list);
		
		list.add("ss");
		list.add("ss");
		setList();
		
		System.out.println(list);
	
		
	}
	
	private static List<String > setList(){
		List<String> newlist=null;
		if(list==null) {
			newlist=new ArrayList<>();
			newlist.add("rr");
			newlist.add("aa");
			newlist.add("rr");
		}else {
			newlist=list;
		}
		
		
		newlist=	newlist.stream().distinct().collect(Collectors.toList());
		
		return newlist;
	}

}
