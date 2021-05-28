package com.control.pad;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;

public class PadFactory {
	public final static String MAINEXPLANATIONFRAME_EXPLANATION = "mainexplanationframe_explanation";
	public final static String MAINEXPLANATIONFRAME_EXAMPLE = "mainexplanationframe_example";
	public final static String MAINADDVOCABULARYFRAME_EXPLANATION = "mainaddvocabularyframe_explanation";
	public final static String MAINADDVOCABULARYFRAME_EXAMPLE = "mainaddvocabularyframe_example";

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
