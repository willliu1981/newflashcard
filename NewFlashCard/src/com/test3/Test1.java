package com.test3;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test1 {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date testDate = sdf.parse(null);

	}

}
