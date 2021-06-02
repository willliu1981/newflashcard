package com.control.viewcontrol.bridge;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.control.bridge.Bridge;
import com.control.dao.VocabularyDao;
import com.model.Vocabulary;
import com.view.MainView;

public class AddVocabularyUpdateBridge extends Bridge {

	@Override
	public void doSend() {
		VocabularyDao dao = new VocabularyDao();
		Vocabulary vocabulary = (Vocabulary) this.getParameter("vocabulary");
		Component parent = (Component) this.getParameter("parent");
		if (vocabulary.getVocabulary() == null || vocabulary.getVocabulary().equals("")) {
			JOptionPane.showMessageDialog(parent, "詞彙為必要的資料", "資料錯誤", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (vocabulary.getTranslation() == null || vocabulary.getTranslation().equals("")) {
			JOptionPane.showMessageDialog(parent, "翻譯為必要的資料", "資料錯誤", JOptionPane.WARNING_MESSAGE);
			return;
		}

		dao.update(vocabulary, vocabulary.getId());

		this.setParameter("fetch",false);
		this.setParameter("vocabulary",null);
		this.getDispatcher().send(MainView.addVocabularyFrame);
	}

}
