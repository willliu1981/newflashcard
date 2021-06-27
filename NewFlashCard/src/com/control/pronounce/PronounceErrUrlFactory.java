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
import java.util.List;

import com.google.gson.Gson;
import com.model.ErrPronounceURLModel;

public class PronounceErrUrlFactory {
	private static ErrPronounceURLModel epUrlModel;
	private static final String path = "data/sound/errdata.txt";
	static {
		Gson g = new Gson();
		String str = null;
		if ((str = load()) != null) {
			epUrlModel = g.fromJson(str, ErrPronounceURLModel.class);
		}
	}

	private static ErrPronounceURLModel getErrPronounceURLModel() {
		if (epUrlModel == null) {
			epUrlModel = new ErrPronounceURLModel();
		}
		return epUrlModel;
	}

	public static boolean isContainError(String vocabulary) {
		return getErrPronounceURLModel().isExist(vocabulary);
	}

	public static void add(String vocabulary) {
		getErrPronounceURLModel().add(vocabulary);
	}

	public static void date() {
		getErrPronounceURLModel().setDate(new Date());
	}

	public static void write() {
		Gson g = new Gson();
		write(g.toJson(getErrPronounceURLModel()));
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

	public static void initialize() {
		epUrlModel.initialize();
	}

	public static boolean isEffectiveListChanged() {
		boolean r = false;
		List<String> oriList = PronounceFormatStrFactory.PronounceFormatStrTool
				.transformToEffectiveList(PronounceFormatStrFactory.getNewFromProperties());
		List<String> currentList = PronounceFormatStrFactory.getEffectiveList();
		r = oriList.containsAll(currentList) && currentList.containsAll(oriList);
		return !r;
	}

}
