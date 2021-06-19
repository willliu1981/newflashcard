package com.test2;

import java.io.File;
import java.io.IOException;

public class Test7 {

	public static void main(String[] args) throws IOException {

		
		String path="c:/test/b/b1/bb1";
		File f=new File(path);
		System.out.println(f.exists());
		
		if(!f.exists()) {
			f.mkdir();
		}
		
	}

}
