package com.control.pronounce;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tool.PropertiesFactory;

public class PronounceFactory {
	private static final String propertiesKey = "pronounce_url_formatstr";
	private static final List<String> list;
	private static final List<String> listEffective;
	
	static {
		String pronounce_url_formatstr = PropertiesFactory.getInstance().getProperty(propertiesKey);
		String[] strs = pronounce_url_formatstr.split(";");
		list = Stream.of(strs).collect(Collectors.toList());
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
		PropertiesFactory.getInstance().setProperty(propertiesKey, str);
		PropertiesFactory.store();
	}

	public static void add(String formatStr) {
		list.add(formatStr);
		setEffectiveList();
	}

	public static void add(int index, String formatStr) {
		list.add(index, formatStr);
		setEffectiveList();
	}



	public static void update(int index, String str) {
		list.set(index, str);
		setEffectiveList();
	}

	public static void remove(int index) {
		list.remove(index);
		setEffectiveList();
	}

	public static int moveUp(int index) {
		if (index <= 0) {
			return -1;
		}

		int up = index - 1;
		list.add(up, list.remove(index));
		
		setEffectiveList();
		
		return up;
	}

	public static int moveDown(int index) {
		if (index >= list.size() - 1) {
			return -1;
		}

		int down = index + 1;
		list.add(down, list.remove(index));
		
		setEffectiveList();
		
		return down;
	}

	public static String[] getEffectiveArr() {
		String[] arr = new String[listEffective.size()];
		return listEffective.toArray(arr);
	}
}
