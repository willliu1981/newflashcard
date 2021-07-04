package com.test2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Test12 {


	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties prop=new Properties();
		prop.load(new FileInputStream(new File("data/test.properties")));
		

	}

}
