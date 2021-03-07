package com.control.viewcontrol;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ShowRowInfo {
	public static final String ID = "id";
	public static final String Name = "name";
	public static final String Test_time = "test_time";
	public static final String Test_date = "test_date";
	public static final String Create_date = "create_date";
	public static final String Update_date = "update_date";
	public static final String Vocabulary_Quantity = "vocabulary_quantity";
	public static final String CardBox_Quantity = "cardbox_quantity";
	public static final String CardBox_Vocabulary_Quantity = "cardbox_vocabulary_quantity";
	public static final String Test_Quantity = "test_quantity";
	public static final String Vocabulary = "vocabulary";
	public static final String Translation = "translation";
	public static final String BoxId = "boxid";
	public static final String Cardbox_Editbar_add_lock = "cardbox_editbar_add_lock";
	public static final String EditbarEditPanel = "editbareditpanel";
	public static final String EditbarEditPanel_Field = "editbareditfield";
	public static final String InfoName_Default = "info_default";
	public static final String InfoName_CardBox = "info_cardbox";
	public static final String InfoName_Vocabulary = "info_vocabulary";
	public static final String InfoName_CardBox_Vocabulary = "info_cardbox_vocabulary";
	public static final String InfoName_Test = "info_test";
	public static final String InfoName_Vocabulary_Editbar_Add = "info_vocabulary_editbar_add";
	public static final String InfoName_Vocabulary_Editbar_Edit = "info_vocabulary_editbar_edit";// click row to edit
	public static final String InfoName_CardBox_Vocabulary_Editbar_Add = "info_cardbox_vocabulary_editbar_add";
	public static final String InfoName_CardBox_Vocabulary_Editbar_Edit = "info_cardbox_vocabulary_editbar_edit";
	public static final String InfoName_CardBox_Vocabulary_Editbar_Remove = "info_cardbox_vocabulary_editbar_remove";
	public static final String InfoName_CardBox_Editbar_Add = "info_cardbox_editbar_add";
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

	public Component getComponent(String name) {
		return this.comps.get(name);
	}

	public static ShowRowInfo findInfo(ShowRowInfo info, String infoName) {
		if (info == null) {
			return null;
		}

		if (info.getInfoName().equals(infoName)) {
			return info;
		} else {
			return findInfo(info.getInfo(), infoName);
		}
	}

}
