package com.control.viewcontrol.bridge;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.control.bridge.Bridge;
import com.control.dao.CardBoxDao;
import com.control.dao.VocabularyDao;
import com.model.CardBox;
import com.model.Vocabulary;
import com.tool.Mask;
import com.view.MainView;

public class AddVocabularyQueryBridge extends Bridge {

	@Override
	public void doSend() {
		Component parent = (Component) this.getParameter("parent");
		String vocabulary = (String) this.getParameter("vocabulary");

		List<Vocabulary> vocabularies = query(vocabulary);
		Vocabulary result = null;
		if (vocabularies.size() == 0) {
			JOptionPane.showMessageDialog(parent, "查無資料");
			return;
		} else if (vocabularies.size() > 1) {
			class VocabularyString {
				String content;

				VocabularyString(String translation) {
					this.content = translation;
				}

				public String toString() {
					return this.content;
				}
			}

			VocabularyString[] arrvstr = new VocabularyString[vocabularies.size()];
			Map<VocabularyString, Vocabulary> map = new HashMap<>();
			vocabularies.stream().map(x -> {
				VocabularyString vstr = new VocabularyString(x.getTranslation());
				map.put(vstr, x);
				return vstr;
			}).collect(Collectors.toList()).toArray(arrvstr);

			VocabularyString vstrResult = (VocabularyString) JOptionPane.showInputDialog(parent,
					"找到多筆資料,是否取出資料?\n確認後請選擇一筆資料", "查詢資料", JOptionPane.INFORMATION_MESSAGE, null, arrvstr, arrvstr[0]);
			result = map.get(vstrResult);
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

		CardBoxDao dao=new CardBoxDao();
		CardBox box=dao.query(result.getBox_id());
		
		this.setParameter("vocabulary", result);
		this.setParameter("cardbox", box);
		this.setParameter("fetch", true);
		this.getDispatcher().send(MainView.addVocabularyFrame);
	}

	@Override
	public   Mask doSendAndBack() {
		List<Vocabulary> vocabularies = query((String) this.getParameter("vocabulary"));
		if (vocabularies.size() > 0) {
			return SENDANDBACK_NORMAL.add(SENDANDBACK_INTERRUPT);
		} 
		return SENDANDBACK_DEFAULT.add(SENDANDBACK_INTERRUPT);
	}

	private List<Vocabulary> query(String query) {
		VocabularyDao dao = new VocabularyDao();
		List<Vocabulary> vocabularies = dao.queryByVocabulary(query);
		return vocabularies;
	}

}
