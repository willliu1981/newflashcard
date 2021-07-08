package com.control.pad;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tool.Mask;
import com.tool.MyColor;

public class PadFactory {
	public final static String EXPLANATIONFRAME = "explanationframe";
	public final static String ADDVOCABULARYFRAME = "addvocabularyframe";
	public final static String MAIN_EXPLANATIONFRAME_EXPLANATION = "main_explanationframe_explanation";
	public final static String MAIN_EXPLANATIONFRAME_EXAMPLE = "main_explanationframe_example";
	public final static String MAIN_EXPLANATIONFRAME_TRANSLATION = "main_explanationframe_translation";
	public final static String MAIN_ADDVOCABULARYFRAME_EXPLANATION = "main_addvocabularyframe_explanation";
	public final static String MAIN_ADDVOCABULARYFRAME_EXAMPLE = "main_addvocabularyframe_example";
	public final static String MAIN_ADDVOCABULARYFRAME_TRANSLATION = "main_addvocabularyframe_translation";
	public final static String VOCABULARY_TRANSLATION_EXPLANATION_EXAMPLE = "vocabulary_translation_explanation_example";
	public final static String TRANSLATION_EXPLANATION_EXAMPLE = "translation_explanation_example";
	public final static Mask FRAME_EXPLANATION = new Mask(1);// "explanationframe";
	public final static Mask FRAME_ADDVOCABULARY = new Mask(2);// "addvocabularyframe";
	public final static Mask EXPLANATION = new Mask(4);// "main_explanationframe_explanation";
	public final static Mask EXAMPLE = new Mask(8);// "main_explanationframe_example";
	public final static Mask TRANSLATION = new Mask(16);// "main_explanationframe_translation";
	public final static Mask VOCABULARY = new Mask(32);// "vocabulary";
	public final static Mask SEARCH_INTERCEPT = new Mask(1);
	public final static Mask SEARCH_INPUT = new Mask(2);
	public final static Mask SEARCH_INPUT_COMBOBOX = new Mask(4);

	private static PadFactory factory = new TextPadFactory();
	private Pad pad;

	public static Pad getPad() {
		factory.initialize();
		return factory.pad;
	}

	private void initialize() {
		if (pad == null) {
			pad = new MyPad2();
		}
	}

	/*
	 * note : 此方法已無效,並由子類別實作
	 */
	protected boolean _isChanged(String frame) {
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

	protected boolean _isChanged(Mask frame) {
		return getPad().getMaskPadPackMapKeys().stream().filter(x -> {
			if (x.has(frame)) {
				if (!getPad().getFirstContent(x).equals(getPad().getContentTemp(x))) {
					return true;
				}
			}
			return false;
		}).findAny().isPresent();
	}

	public static boolean isChanged(String frame) {
		return factory._isChanged(frame);
	}

	public static boolean isChanged(Mask mask) {
		return factory._isChanged(mask);
	}

	public static void change(Component parent, Mask mask, KeyEvent e) {
		if (PadFactory.isChanged(mask)) {
			parent.setBackground(Color.red);
		} else {
			parent.setBackground(MyColor.defaultColor());
		}
	}

	public void _setFirstContents(Mask frame, String typeSequence, String[] contents) {
		switch (typeSequence) {
		case VOCABULARY_TRANSLATION_EXPLANATION_EXAMPLE:
			getPad().setFirstContent(frame.add(PadFactory.VOCABULARY), contents[0]);
			getPad().setFirstContent(frame.add(PadFactory.TRANSLATION), contents[1]);
			getPad().setFirstContent(frame.add(PadFactory.EXPLANATION), contents[2]);
			getPad().setFirstContent(frame.add(PadFactory.EXAMPLE), contents[3]);
			break;
		case TRANSLATION_EXPLANATION_EXAMPLE:
			getPad().setFirstContent(frame.add(PadFactory.TRANSLATION), contents[0]);
			getPad().setFirstContent(frame.add(PadFactory.EXPLANATION), contents[1]);
			getPad().setFirstContent(frame.add(PadFactory.EXAMPLE), contents[2]);
			break;
		default:
			break;

		}
	}

	public static void setFirstContents(Mask frame, String typeSequence, String[] contents) {
		factory._setFirstContents(frame, typeSequence, contents);
	}

	public void _initializeContentTemp(Mask frame, String typeSequence, String[] contents) {
		switch (typeSequence) {
		case VOCABULARY_TRANSLATION_EXPLANATION_EXAMPLE:
			getPad().setContentTemp(frame.add(PadFactory.VOCABULARY), contents[0]);
			getPad().setContentTemp(frame.add(PadFactory.TRANSLATION), contents[1]);
			getPad().setContentTemp(frame.add(PadFactory.EXPLANATION), contents[2]);
			getPad().setContentTemp(frame.add(PadFactory.EXAMPLE), contents[3]);
			break;
		case TRANSLATION_EXPLANATION_EXAMPLE:
			getPad().setContentTemp(frame.add(PadFactory.TRANSLATION), contents[0]);
			getPad().setContentTemp(frame.add(PadFactory.EXPLANATION), contents[1]);
			getPad().setContentTemp(frame.add(PadFactory.EXAMPLE), contents[2]);
			break;
		default:
			break;
		}
	}

	public static void initializeContentTemp(Mask frame, String typeSequence, String[] contents) {
		factory._initializeContentTemp(frame, typeSequence, contents);
	}

	public static boolean query(Component parent, String vocabulary) {
		return getPad().query(parent, vocabulary);
	}

	public static boolean query(Component parent, String vocabulary, Mask mask) {
		return getPad().query(parent, vocabulary, mask);
	}

	public static boolean query(Component parent, String vocabulary, Mask mask, int limit) {
		return getPad().query(parent, vocabulary, mask, limit);
	}

}
