package com.control.viewcontrol;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ShowRowInfo {
	public static final String ID = "id";
	public static final String Name = "name";
	public static final String Create_date = "create_date";
	public static final String Update_date = "update_date";
	public static final String Vocabulary_Quantity = "vocabulary_quantity";
	public static final String CardBox_Quantity = "cardbox_quantity";
	public static final String CardBox_Vocabulary_Quantity = "cardbox_vocabulary_quantity";
	public static final String InfoName_Default = "info_default";
	public static final String InfoName_CardBox = "info_cardbox";
	public static final String InfoName_Vocabulary = "info_vocabulary";
	public static final String InfoName_CardBox_Vocabulary = "info_cardbox_vocabulary";

	protected Map<String, Component> comps = new HashMap<>();
	protected ShowRowInfo info;
	protected String infoName;

	public ShowRowInfo() {
		this(InfoName_Default);
	}

	public ShowRowInfo(String infoName) {
		this.infoName = infoName;
	}

	public ShowRowInfo(ShowRowInfo info) {
		this.info = info;
	}

	public void add(Component comp, String name) {
		comps.put(name, comp);
	}

	public ShowRowInfo getInfo() {
		return info;
	}

	public void setInfo(ShowRowInfo info) {
		this.info = info;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public abstract void showInfo(Map<String, String> map);

}
