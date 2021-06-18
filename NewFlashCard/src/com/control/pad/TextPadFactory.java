package com.control.pad;

public class TextPadFactory extends PadFactory {

	public boolean _isChanged(String frame) {
		return this._isChanged(TextPad.adaptType(frame));
	}




}
