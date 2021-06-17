package com.control.pad;

import java.awt.Component;
import java.awt.event.KeyEvent;

import com.tool.Mask;

public abstract class TextPad extends Pad {

	@Override
	public String getContentTemp(String name) {
		String content = "";
		switch (name) {
		case PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE:
			content = super.getContentTemp(PadFactory.FRAMEADDVOCABULARY.add(PadFactory.EXAMPLE));
			break;
		case PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION:
			content = super.getContentTemp(PadFactory.FRAMEADDVOCABULARY.add(PadFactory.EXPLANATION));
			break;
		case PadFactory.MAIN_ADDVOCABULARYFRAME_TRANSLATION:
			content = super.getContentTemp(PadFactory.FRAMEADDVOCABULARY.add(PadFactory.TRANSLATION));
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE:
			content = super.getContentTemp(PadFactory.FRAMEEXPLANATION.add(PadFactory.EXAMPLE));
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION:
			content = super.getContentTemp(PadFactory.FRAMEEXPLANATION.add(PadFactory.EXPLANATION));
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_TRANSLATION:
			content = super.getContentTemp(PadFactory.FRAMEEXPLANATION.add(PadFactory.TRANSLATION));
			break;
		default:
			break;
		}
		return content;
	}

	@Override
	public void setContentTemp(String name, String content) {
		switch (name) {
		case PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE:
			super.setContentTemp(PadFactory.FRAMEADDVOCABULARY.add(PadFactory.EXAMPLE), content);
			break;
		case PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION:
			super.setContentTemp(PadFactory.FRAMEADDVOCABULARY.add(PadFactory.EXPLANATION), content);
			break;
		case PadFactory.MAIN_ADDVOCABULARYFRAME_TRANSLATION:
			super.setContentTemp(PadFactory.FRAMEADDVOCABULARY.add(PadFactory.TRANSLATION), content);
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE:
			super.setContentTemp(PadFactory.FRAMEEXPLANATION .add(PadFactory.EXAMPLE), content);
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION:
			super.setContentTemp(PadFactory.FRAMEEXPLANATION.add(PadFactory.EXPLANATION), content);
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_TRANSLATION:
			super.setContentTemp(PadFactory.FRAMEEXPLANATION.add(PadFactory.TRANSLATION), content);
			break;
		default:
			break;
		}
	}

	

}
