package com.control.pad;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.text.JTextComponent;

import com.control.dao.VocabularyDao;
import com.control.viewcontrol.bridge.QueryResultBridge;
import com.model.Vocabulary;
import com.tool.Mask;

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



	@Override
	public void _initializeChange(String frame) {

	}

}
