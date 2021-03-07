package com.control.viewcontrol;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public abstract class InfoProperty {
	public static final String Id = "id";
	public static final String Vocabulary = "vocabulary";
	public static final String Translation = "translation";
	public static final String Quantity = "quantity";
	public static final String Test_Time = "test_time";
	public static final String Test_Date = "test_date";
	public static final String Create_Date = "create_date";
	public static final String Update_Date = "update_date";
	public static final String CardBox = "card_box";
	public static final String Name = "name";
	public static final String Test  = "test";
	public static final String Create  = "create";
	public static final String EditName  = "edit_name";
	

	private static Properties prop;

	public static String getInfo(String infoName) {
		if (prop == null) {
			prop = new Properties();
			try {
				prop.load(new FileInputStream("data\\info.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop.getProperty(infoName);
	}

}