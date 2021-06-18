package com.control.pad;

import com.tool.Mask;

public abstract class TextPad extends Pad {

	@Override
	public String getContentTemp(String name) {
		return this.getContentTemp(this.adaptType(name));
	}

	@Override
	public void setContentTemp(String name, String content) {
		this.setContentTemp(this.adaptType(name), content);
	}

	public static Mask adaptType(String name) {
		Mask mask = null;
		switch (name) {
		case PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE:
			mask = PadFactory.FRAME_ADDVOCABULARY.add(PadFactory.EXAMPLE);
			break;
		case PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION:
			mask = PadFactory.FRAME_ADDVOCABULARY.add(PadFactory.EXPLANATION);
			break;
		case PadFactory.MAIN_ADDVOCABULARYFRAME_TRANSLATION:
			mask = PadFactory.FRAME_ADDVOCABULARY.add(PadFactory.TRANSLATION);
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE:
			mask = PadFactory.FRAME_EXPLANATION.add(PadFactory.EXAMPLE);
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION:
			mask = PadFactory.FRAME_EXPLANATION.add(PadFactory.EXPLANATION);
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_TRANSLATION:
			mask = PadFactory.FRAME_EXPLANATION.add(PadFactory.TRANSLATION);
			break;
		case PadFactory.ADDVOCABULARYFRAME:
			mask = PadFactory.FRAME_ADDVOCABULARY;
			break;
		case PadFactory.EXPLANATIONFRAME:
			mask = PadFactory.FRAME_EXPLANATION;
			break;
		default:
			mask = new Mask(0);
			break;
		}
		return mask;
	}

}
