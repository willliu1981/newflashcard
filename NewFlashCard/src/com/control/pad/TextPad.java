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
			mask = PadFactory.FRAMEADDVOCABULARY.add(PadFactory.EXAMPLE);
			break;
		case PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION:
			mask = PadFactory.FRAMEADDVOCABULARY.add(PadFactory.EXPLANATION);
			break;
		case PadFactory.MAIN_ADDVOCABULARYFRAME_TRANSLATION:
			mask = PadFactory.FRAMEADDVOCABULARY.add(PadFactory.TRANSLATION);
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE:
			mask = PadFactory.FRAMEEXPLANATION.add(PadFactory.EXAMPLE);
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION:
			mask = PadFactory.FRAMEEXPLANATION.add(PadFactory.EXPLANATION);
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_TRANSLATION:
			mask = PadFactory.FRAMEEXPLANATION.add(PadFactory.TRANSLATION);
			break;
		case PadFactory.ADDVOCABULARYFRAME:
			mask = PadFactory.FRAMEADDVOCABULARY;
			break;
		case PadFactory.EXPLANATIONFRAME:
			mask = PadFactory.FRAMEEXPLANATION;
			break;
		default:
			mask = new Mask(0);
			break;
		}
		return mask;
	}

}
