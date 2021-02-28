package com.test;

import java.util.Arrays;

public class Test2 {
	EE e;
	
  public	enum EE{
		E1,E2,E3
		
	}

	public static void main(String[] args) {
		Test2 t=new Test2();
		t.e=EE.E1;
		
		Arrays.asList(EE.values()).stream().forEach(System.out::println);
		
		

	}

}
