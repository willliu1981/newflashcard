package com.control.pad;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;

public class PadFactory {
	public final static String MAIN_EXPLANATIONFRAME_EXPLANATION = "main_explanationframe_explanation";
	public final static String MAIN_EXPLANATIONFRAME_EXAMPLE = "main_explanationframe_example";
	public final static String MAIN_ADDVOCABULARYFRAME_EXPLANATION = "main_addvocabularyframe_explanation";
	public final static String MAIN_ADDVOCABULARYFRAME_EXAMPLE = "main_addvocabularyframe_example";

	private class MyPad extends Pad {
		@Override
		public void change(Component parent, String name, KeyEvent e) {
			/*
			 * 非按下Ctrl+A 和 Ctrl+C
			 */
			if ((int) e.getKeyChar() != 1 && (int) e.getKeyChar() != 3) {
				parent.setBackground(Color.red);
				this.setChange(name);
			}
		}
	}

	private static PadFactory factory = new PadFactory();
	private Pad pad = new MyPad();

	public static Pad getPad() {
		return factory.pad;
	}
}
