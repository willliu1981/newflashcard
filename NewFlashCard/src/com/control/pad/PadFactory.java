package com.control.pad;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;

import javax.swing.text.JTextComponent;

public class PadFactory {
	public final static String EXPLANATIONFRAME = "explanationframe";
	public final static String ADDVOCABULARYFRAME = "addvocabularyframe";
	public final static String MAIN_EXPLANATIONFRAME_EXPLANATION = "main_explanationframe_explanation";
	public final static String MAIN_EXPLANATIONFRAME_EXAMPLE = "main_explanationframe_example";
	public final static String MAIN_EXPLANATIONFRAME_TRANSLATION = "main_explanationframe_translation";
	public final static String MAIN_ADDVOCABULARYFRAME_EXPLANATION = "main_addvocabularyframe_explanation";
	public final static String MAIN_ADDVOCABULARYFRAME_EXAMPLE = "main_addvocabularyframe_example";
	public final static String MAIN_ADDVOCABULARYFRAME_TRANSLATION = "main_addvocabularyframe_translation";

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

	public static boolean isChanged(String name) {
		Pad pad = getPad();
		boolean r = false;
		switch (name) {
		case EXPLANATIONFRAME:
			r = pad.isChanged(MAIN_EXPLANATIONFRAME_EXPLANATION) || pad.isChanged(MAIN_EXPLANATIONFRAME_EXAMPLE)
					|| pad.isChanged(MAIN_EXPLANATIONFRAME_TRANSLATION);
			break;
		case ADDVOCABULARYFRAME:
			break;
		default:
			break;
		}
		return r;
	}

	public static void initializeChange(String name) {
		Pad pad = getPad();
		switch (name) {
		case EXPLANATIONFRAME:
			pad.setChange(MAIN_EXPLANATIONFRAME_EXPLANATION, false);
			pad.setChange(MAIN_EXPLANATIONFRAME_EXAMPLE, false);
			pad.setChange(MAIN_EXPLANATIONFRAME_TRANSLATION, false);
			break;
		case ADDVOCABULARYFRAME:
			break;
		default:
			break;
		}
	}
	
	public static void setKeymap(JTextComponent comp) {
		getPad().setKeymap(comp);
	}
}
