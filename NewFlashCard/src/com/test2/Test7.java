package com.test2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tool.PropertiesFactory;

public class Test7 {
	
	private static  List<String> list;
	


	public static void main(String[] args) throws IOException {
		String [] strs= {"aa","bb","cc"};
		list=Stream.of(strs).collect(Collectors.toList());

		System.out.println(list);
		
		list.set(1, "pp");

		list.add("sdf");
		
		System.out.println(list);
	
		PropertiesFactory.getInstance().setProperty("rrr", "kkkk");
	System.out.println("prop "+	PropertiesFactory.getInstance().getProperty("rrr"));
	
	PropertiesFactory.getInstance().put("sssss", "xxxx");
		
	}
	


}
