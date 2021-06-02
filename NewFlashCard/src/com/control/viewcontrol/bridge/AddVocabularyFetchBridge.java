package com.control.viewcontrol.bridge;

import java.awt.Component;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.control.bridge.Bridge;
import com.control.dao.VocabularyDao;
import com.model.Vocabulary;
import com.view.MainView;

public class AddVocabularyFetchBridge extends Bridge {

	@Override
	public void doSend() {
		Component parent = (Component) this.getParameter("parent");
		String vocabulary = (String) this.getParameter("vocabulary");

		VocabularyDao dao = new VocabularyDao();
		List<Vocabulary> vocabularies = dao.queryByVocabulary(vocabulary);
		Vocabulary result = null;
		if (vocabularies.size() == 0) {
			JOptionPane.showMessageDialog(parent, "查無資料");
			return ;
		} else if (vocabularies.size() > 1) {
			 class MyVocabulary extends Vocabulary{
				@Override
				public String toString() {
					return this.getTranslation();
				}
			}
			
			Vocabulary[] arrVocabulary = new MyVocabulary[vocabularies.size()];
			vocabularies.stream().map(x->{
				MyVocabulary v= new MyVocabulary();
				v.setVocabulary(x.getVocabulary());
				v.setTranslation(x.getTranslation());
				v.setExplanation(x.getExplanation());
				v.setExample(x.getExample());
				return v;
			}).collect(Collectors.toList()).toArray(arrVocabulary);
			result = (Vocabulary) JOptionPane.showInputDialog(parent, "找到多筆資料\n請選擇一筆資料", "查詢資料", JOptionPane.INFORMATION_MESSAGE, null,
					arrVocabulary, arrVocabulary[0]);
		}else {
			result=vocabularies.get(0);
		}
		if(result==null) {
			return;
		}
		
		this.setParameter("vocabulary", result);
		this.getDispatcher().send(MainView.addVocabularyFrame);
	}

}
