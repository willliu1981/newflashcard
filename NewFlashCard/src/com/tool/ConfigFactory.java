package com.tool;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigFactory {
	private static final String path="data/config.properties";
	private static final Properties prop=new Properties();
	
	 static {
		try {
			prop.load(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Color getColor(String element) {
		Color color=null;
		String[] colors=prop.getProperty(element).split(",");
		List<Integer> icolors= Stream.of(colors).map(x->Integer.valueOf(x)).collect(Collectors.toList());
		color=new Color(icolors.get(0),icolors.get(1),icolors.get(2));
		return color;
	}
	
	public static Properties getPropInstance() {
		return prop;
	}
	
	public static void store() {
		try {
			prop.store(new  FileOutputStream(path), "test");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
