package com.control.pad;

public class TextPadFactory extends PadFactory {

	public boolean _isChanged(String frame) {
		boolean r = false;

		switch (frame) {
		case PadFactory.ADDVOCABULARYFRAME:
			r=this._isChanged(PadFactory.FRAMEADDVOCABULARY);
			break;

		case PadFactory.EXPLANATIONFRAME:
			r=this._isChanged(PadFactory.FRAMEEXPLANATION);
			break;
		default:
			break;
		}

		return r;
	}




}
