package com.test2;

import java.util.Calendar;

public class Test3 {

	public static void main(String[] args) {
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		boolean sameDay = c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
			    c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);

		System.out.format("%s , %s\n",c1.getTime(),c2.getTime());
		System.out.println(c1);
		
	}

}
