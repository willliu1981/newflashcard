package com.test2;

import java.io.File;
import java.io.IOException;

public class Test10 {

	public static void main(String[] args) throws IOException {


		String fstr="data/sss/";
		String f2str="data/sss/";
		String f3str="data/sample.db";
		
		
		File f=new File(fstr);
		File f2=new File(f2str);
		File f3=new File(f3str);
		
		/*
		if(!f.exists()) {
			f.mkdirs();
		}
		f2.createNewFile();
		
		System.out.println(f3.getParent());
		*
		*/
	
		if(!f2.exists()) {
			f2.mkdir();


		}
	}

}
