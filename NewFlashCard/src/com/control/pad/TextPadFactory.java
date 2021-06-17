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

	public  boolean isChanged(String frame) {
		Pad pad = getPad();
		boolean r = false;

		return r;
	}

	public  void initializeChange(Mask frame) {
		Pad pad = getPad();
		if (frame.has(EXPLANATIONFRAME)) {

		} else if (frame.has(ADDVOCABULARYFRAME)) {

		}
	}

}
