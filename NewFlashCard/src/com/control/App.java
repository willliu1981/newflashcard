package com.control;

import javax.swing.JFrame;

import com.tool.ConfigFactory;
import com.view.MainView;

public class App {
	private static final String version="1.0";
	private  static JFrame mainView;
	
	
	
	public static JFrame initialize() {
		if(mainView==null) {
			mainView=new MainView();
		}
		mainView.setVisible(true);

		String deisgn =  ConfigFactory.getPropInstance(). getProperty("design");
		String msg = version;
		if (deisgn.equals("true")) {
			msg += " Debug";
		}
		mainView.setTitle(String.format("字卡 App 版本 %s", msg));
		return mainView;
	}
	
	public static String getVersion() {
		return version;
	}
}
