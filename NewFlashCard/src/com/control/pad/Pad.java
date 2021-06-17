package com.control.pad;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.text.JTextComponent;

import com.tool.Mask;

public abstract class Pad {
	private static class PadPack {
		private Optional<String> contentTemp;
		private String reverseContent;
		private String firstContent;
		private boolean isChanged;

		public PadPack() {
			this.contentTemp = Optional.empty();
			this.reverseContent = "";
			this.isChanged = false;
		}

		public String getContentTemp() {
			return contentTemp.orElse("");
		}

		public void setContentTemp(String contentTemp) {
			this.contentTemp = Optional.of(contentTemp);
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

		public String getFirstContent() {
			return firstContent;
		}

		public void setFirstContent(String firstContent) {
			this.firstContent = firstContent;
		}

	}

	protected Pad() {
	}

	protected static Map<String, PadPack> padPackMap = new HashMap<>();
	protected static Map<Mask, PadPack> mask_padPackMap = new HashMap<>();

	public boolean containsKey(String name) {
		return this.padPackMap.containsKey(name);
	}

	protected PadPack getPadPack(String name) {
		if (!this.padPackMap.containsKey(name)) {
			this.padPackMap.put(name, new PadPack());
		}
		return this.padPackMap.get(name);
	}
	
	protected PadPack getMaskPadPack(Mask mask) {
		if (!this.mask_padPackMap.containsKey(mask)) {
			this.mask_padPackMap.put(mask, new PadPack());
		}
		return this.mask_padPackMap.get(mask);
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
	
	public void setContentTemp(Mask mask, String content) {
		getMaskPadPack(mask).setContentTemp(content);
	}
	
	public String getContentTemp(Mask mask) {
		return getMaskPadPack(mask).getContentTemp();
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

	public void setFirstContent(String name, JTextComponent... comps) {
		getPadPack(name)
				.setFirstContent(Stream.of(comps).map(JTextComponent::getText).reduce((x1, x2) -> x1 + x2).orElse(""));
	}

	public abstract void change(Component parent, String name, KeyEvent e);

	public void keyAction_pressed(String name, JTextComponent comp, KeyEvent e) {

	}

	public void keyAction_typed(String name, JTextComponent comp, KeyEvent e) {

	}

	public void keyAction_release(String name, JTextComponent comp, KeyEvent e) {

	}

	public void initializeContent(String name) {
		initializeContent(name, "");
	}

	public void initializeContent(String name, String content) {
		getPadPack(name).setContentTemp(content);
		getPadPack(name).setReverseContent(content);
	}

	public boolean query(Component parent, String str) {
		return query(parent, str);
	}

	public boolean query(Component parent, String str, Mask mask) {
		return query(parent, str, mask, -1);
	}

	public boolean query(Component parent, String str, Mask mask, int limit) {
		return false;
	}

}
