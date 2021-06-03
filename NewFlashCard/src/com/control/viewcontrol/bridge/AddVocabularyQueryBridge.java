package com.control.viewcontrol.bridge;

import java.awt.Component;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.control.bridge.Bridge;
import com.control.dao.VocabularyDao;
import com.model.Vocabulary;
import com.view.MainView;

public class AddVocabularyQueryBridge extends Bridge {

	@Override
	public void doSend() {
		Component parent = (Component) this.getParameter("parent");
		String vocabulary = (String) this.getParameter("vocabulary");

		VocabularyDao dao = new VocabularyDao();
		List<Vocabulary> vocabularies = dao.queryByVocabulary(vocabulary);
		Vocabulary result = null;
		if (vocabularies.size() == 0) {
			JOptionPane.showMessageDialog(parent, "查無資料");
			return;
		} else if (vocabularies.size() > 1) {
			class MyVocabulary extends Vocabulary {
				protected MyVocabulary(Integer id, String vocabulary, String translation, String explanation,
						String example, Integer box_id, Integer test_time, String test_date, String create_date,
						String update_date) {
					super(id, vocabulary, translation, explanation, example, box_id, test_time, test_date, create_date,
							update_date);
				}

				@Override
				public String toString() {
					return this.getTranslation();
				}
			}

			Vocabulary[] arrVocabulary = new MyVocabulary[vocabularies.size()];
			vocabularies.stream().map(x -> {
				MyVocabulary v = new MyVocabulary(x.getId(), x.getVocabulary(), x.getTranslation(), x.getExplanation(),
						x.getExample(), x.getBox_id(), x.getTest_time(), x.getTest_date(), x.getCreate_date(),x.getUpdate_date());
				return v;
			}).collect(Collectors.toList()).toArray(arrVocabulary);
			result = (Vocabulary) JOptionPane.showInputDialog(parent, "找到多筆資料,是否取出資料?\n確認後請選擇一筆資料", "查詢資料",
					JOptionPane.INFORMATION_MESSAGE, null, arrVocabulary, arrVocabulary[0]);
		} else {
			int r = JOptionPane.showConfirmDialog(parent, "找到一筆資料,是否取出資料?\n" + vocabularies.get(0).getTranslation(),
					"查詢資料", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (r == JOptionPane.YES_OPTION) {
				result = vocabularies.get(0);
			} else {
				return;
			}
		}

		if (result == null) {
			return;
		}

		this.setParameter("vocabulary", result);
		this.setParameter("fetch", true);
		this.getDispatcher().send(MainView.addVocabularyFrame);
	}

}
