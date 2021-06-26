package com.control.pronounce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;
import com.model.ErrPronounceURL;

public class PronounceErrUrlFactory {
	private static ErrPronounceURL epUrl;
	private static final String path = "data/sound/errdata.txt";
	static {
		Gson g = new Gson();
		String str = null;
		if ((str = load()) != null) {
			epUrl = g.fromJson(str, ErrPronounceURL.class);
		}
	}
	
	public static void initializeErrPronounceURL() {
		if(epUrl==null) {
			epUrl=new ErrPronounceURL();
		}
	}
	
	public static boolean isError(String vocabulary) {
		return epUrl.isExist(vocabulary);
	}
	
	public static  void add(String vocabulary) {
		initializeErrPronounceURL();
		epUrl.add(vocabulary);
	}
	
	public static void date() {
		initializeErrPronounceURL();
		epUrl.setDate(new Date());
	}

	public static void write() {
		Gson g = new Gson();
		if (epUrl != null) {
			write(g.toJson(epUrl));
		}
	}

	private static String load() {
		File f = new File(path);

		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr);) {
			String read = null;
			StringBuilder sb = new StringBuilder();
			while ((read = br.readLine()) != null) {
				sb.append(read);
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static void write(String str) {
		File f = new File(path);

		try (FileWriter fw = new FileWriter(f); BufferedWriter bw = new BufferedWriter(fw);) {
			bw.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
