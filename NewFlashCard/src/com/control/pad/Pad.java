package com.control.pad;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.JTextComponent;

import com.tool.Mask;

public abstract class Pad {
	private static class PadPack {
		private String contentTemp;
		private String reverseContent;
		private boolean isChanged;

		public void initialize() {
			this.contentTemp = "";
			this.reverseContent = "";
			this.isChanged = false;
		}

		public String getContentTemp() {
			return contentTemp;
		}

		public void setContentTemp(String contentTemp) {
			this.contentTemp = contentTemp;
		}

		public boolean isChanged() {
			return isChanged;
		}

		public void setChanged(boolean isChanged) {
			this.isChanged = isChanged;
		}

		public String getReverseContent() {
			return reverseContent;
		}

		public void setReverseContent(String reverseContent) {
			this.reverseContent = reverseContent;
		}

	}

	protected Pad() {
	}

	protected static Map<String, PadPack> padPackMap = new HashMap<>();

	public boolean containsKey(String name) {
		return this.padPackMap.containsKey(name);
	}

	protected PadPack getPadPack(String name) {
		if (!this.padPackMap.containsKey(name)) {
			this.padPackMap.put(name, new PadPack());
		}
		return this.padPackMap.get(name);
	}

	public String getReverseContent(String name) {
		return getPadPack(name).getReverseContent();
	}

	public void setReverseContent(String name, String content) {
		getPadPack(name).setReverseContent(content);
	}

	public String getContentTemp(String name) {
		return getPadPack(name).getContentTemp();
	}

	public void setContentTemp(String name, String content) {
		getPadPack(name).setContentTemp(content);
	}

	public boolean isChanged(String name) {
		return getPadPack(name).isChanged;
	}

	public void setChange(String name) {
		setChange(name, true);
	}

	public void setChange(String name, boolean change) {
		getPadPack(name).setChanged(change);
	}

	public abstract void change(Component parent, String name, KeyEvent e);

	public void keyAction_pressed(String frame, JTextComponent comp, KeyEvent e) {

	}

	public void keyAction_typed(String frame, JTextComponent comp, KeyEvent e) {

	}

	public void keyAction_release(String frame, JTextComponent comp, KeyEvent e) {

	}

	public void initializeContent(String name) {
		initializeContent(name, "");
	}

	public void initializeContent(String name, String content) {
		getPadPack(name).setContentTemp(content);
		getPadPack(name).setReverseContent(content);
	}
	
	public  boolean query(Component parent, String str) {
		return query (parent,str,null);
	}
	public  boolean query(Component parent, String str,Mask type) {
		return query(parent,str,type,-1);
	}
	public  boolean query(Component parent, String str,Mask type,int limit) {
		return false;
	}
	
	
	
}
