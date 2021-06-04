package com.control.pad;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.control.dao.VocabularyDao;
import com.model.Vocabulary;
import com.view.MainView;

public final class Pads {

	/*
	 * query , to lower
	 */
	public static boolean query(Component parent, String vocabulary) {
		boolean r = false;
		try {
			List<Vocabulary> vs = new VocabularyDao().queryByVocabulary(vocabulary.toLowerCase().trim());
			if (vs.size() == 0) {
				r = false;
			} else {
				r = true;
				MainView.queryResultFrame.query(parent, vs);
			}
		} catch (NullPointerException e) {
			r = false;
		}

		return r;
	}

	public static boolean query(String vocabulary) {
		return query(null, vocabulary);
	}

	

}
