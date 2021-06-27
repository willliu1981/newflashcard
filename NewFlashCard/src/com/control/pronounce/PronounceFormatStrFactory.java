package com.control.pronounce;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tool.ConfigFactory;

public class PronounceFormatStrFactory {
	
	public static class PronounceFormatStrTool {
		public static List<String> transformToEffectiveList(List<String> list) {
			return list.stream().distinct().collect(Collectors.toList());
		}
	}
	
	private static final String propertiesKey = "pronounce_url_formatstr";
	private static final List<String> list;
	private static final List<String> listEffective;
	// 使用 listEffective ,在程式運行"發音"時能更有效率

	static {
		list = getNewFromProperties();
		listEffective = setEffectiveList();
	}

	public static List<String> getNewFromProperties() {
		List<String> list;
		String pronounce_url_formatstr = ConfigFactory.getPropInstance().getProperty(propertiesKey);
		String[] strs = pronounce_url_formatstr.split(";");
		list = Stream.of(strs).collect(Collectors.toList());
		return list;
	}

	private static List<String> setEffectiveList() {
		List<String> newList =PronounceFormatStrTool.transformToEffectiveList(list);
		if (listEffective == null) {
			return newList;
		} else {
			listEffective.clear();
			newList.stream().forEach(x -> listEffective.add(x));
			return listEffective;
		}
	}
	
	public static List<String> getEffectiveList(){
		return listEffective;
	}
	


	public static void stoveProperties() {
		String str = list.stream().reduce((x1, x2) -> x1 + ";" + x2).orElse("");
		ConfigFactory.getPropInstance().setProperty(propertiesKey, str);
		ConfigFactory.store();

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
	public static String[] getArr() {
		String[] arr = new String[list.size()];
		return list.toArray(arr);
	}
}
