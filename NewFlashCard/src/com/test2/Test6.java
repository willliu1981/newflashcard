package com.test2;

import java.util.Optional;

public class Test6 {

	public static void main(String[] args) {

		Optional<String > op=Optional.empty();
		
		
		op=Optional.ofNullable(null);
		
		if(op==null) {
			
			System.out.println(op.get());
		}else {
			System.out.println(op.get());
			
		}
		

	} 

}
