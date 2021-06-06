package com.control.pad;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.text.JTextComponent;

import com.control.dao.VocabularyDao;
import com.control.viewcontrol.bridge.QueryResultBridge;
import com.model.Vocabulary;

public class PadFactory {
	public final static String EXPLANATIONFRAME = "explanationframe";
	public final static String ADDVOCABULARYFRAME = "addvocabularyframe";
	public final static String MAIN_EXPLANATIONFRAME_EXPLANATION = "main_explanationframe_explanation";
	public final static String MAIN_EXPLANATIONFRAME_EXAMPLE = "main_explanationframe_example";
	public final static String MAIN_EXPLANATIONFRAME_TRANSLATION = "main_explanationframe_translation";
	public final static String MAIN_ADDVOCABULARYFRAME_EXPLANATION = "main_addvocabularyframe_explanation";
	public final static String MAIN_ADDVOCABULARYFRAME_EXAMPLE = "main_addvocabularyframe_example";
	public final static String MAIN_ADDVOCABULARYFRAME_TRANSLATION = "main_addvocabularyframe_translation";

	private static PadFactory factory = new PadFactory();
	private Pad pad;

	public static Pad getPad() {
		factory.initialize();
		return factory.pad;
	}

	private void initialize() {
		if (pad == null) {
			pad = new MyPad();
		}
	}

	public static boolean isChanged(String frame) {
		Pad pad = getPad();
		boolean r = false;
		switch (frame) {
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

	public static void initializeChange(String frame) {
		Pad pad = getPad();
		switch (frame) {
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
	
	public static boolean query(Component parent, String vocabulary) {
		return getPad().query(parent, vocabulary);
	}

}
