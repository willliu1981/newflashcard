package com.test2;

import java.util.Calendar;

import com.control.pad.PadFactory;
import com.tool.Mask;

public class Test3 {

	public static void main(String[] args) {

		Mask m1=PadFactory.SEARCH_FUZZY;
		m1.add(PadFactory.SEARCH_INPUT_INVALID);
		
		System.out.println(m1.has(PadFactory.SEARCH_FUZZY));
		System.out.println(m1.has(PadFactory.SEARCH_INPUT_INVALID));
		System.out.println(m1.has(PadFactory.SEARCH_EXACTLY));
	}

}
