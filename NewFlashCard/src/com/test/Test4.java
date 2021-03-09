package com.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test4 {
	public TestTT t;

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//DateFormat df = DateFormat.getDateInstance();
		String dstr = "2021-01-22";
		Calendar c=Calendar.getInstance();

		try {
			//Date d = df.parse("2021/01/22");
			Date d=sdf.parse(dstr);
			System.out.println(d);
			c.setTime(d);
			c.add(Calendar.DAY_OF_MONTH, 9);
			System.out.println(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, 1);
			System.out.println(c.getTime());
			String newdstr=sdf.format(c.getTime());
			System.out.println(newdstr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class Rr {

	}

}
