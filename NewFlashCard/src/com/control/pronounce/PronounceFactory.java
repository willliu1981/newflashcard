package com.control.pronounce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tool.PropertiesFactory;

public class PronounceFactory {
	private static final String propFormatStr = "pronounce_url_formatstr";
	private static final List<String> list;
	private static final List<String> listEffective;
	static {
		String pronounce_url_formatstr = PropertiesFactory.getInstance().getProperty(propFormatStr);
		String[] strs = pronounce_url_formatstr.split(";");
		list = Arrays.asList(strs);
		listEffective = setEffectiveList();
	}

	private static List<String> setEffectiveList() {
		List<String> newList = list.stream().distinct().collect(Collectors.toList());
		if (listEffective == null) {
			return newList;
		} else {
			listEffective.clear();
			newList.stream().forEach(x -> listEffective.add(x));
			return listEffective;
		}
	}

	public static void setProperties() {
		String str = list.stream().reduce((x1, x2) -> x1 + ";" + x2).orElse("");
		PropertiesFactory.getInstance().setProperty(propFormatStr, str);
	}

	public static void add(String formatStr) {
		list.add(formatStr);
		setEffectiveList();
	}

	public static String get(int index) {
		return list.get(index);
	}

	public static void remove(int index) {
		list.remove(index);
		setEffectiveList();
	}

	public static void moveUp(int index) {
		if (index <= 0) {
			return;
		}

		int up = index - 1;

		list.add(up, list.remove(index));
		setEffectiveList();
	}

	public static void moveDown(int index) {
		if (index >= list.size() - 1) {
			return;
		}

		int down = index + 1;

		list.add(down, list.remove(index));
		setEffectiveList();
	}

	public static String[] getFormatStrArr() {
		String[] arr = new String[listEffective.size()];
		return listEffective.toArray(arr);
	}
}
