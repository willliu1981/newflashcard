package com.control.pad;

import java.awt.Component;
import java.util.List;

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
		} else if (vs.size() == 1) {
			r = true;
			MainView.queryResult.query(parent, vs.get(0));
		} else {
			r = true;
			MainView.queryResult.query(parent, vs.get(0));
		}
		}catch( NullPointerException e) {
			r=false;
		}

		return r;
	}

	public static boolean query(String vocabulary) {
		return query(null, vocabulary);
	}

}
