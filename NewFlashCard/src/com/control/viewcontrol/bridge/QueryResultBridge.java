package com.control.viewcontrol.bridge;

import java.util.List;

import com.control.bridge.Bridge;
import com.control.dao.VocabularyDao;
import com.model.Vocabulary;
import com.tool.Mask;
import com.view.MainView;

public class QueryResultBridge extends Bridge {

	@Override
	public void doSend() {
	}

	@Override
	public Mask doSendAndBack() {
		String vocabulary =(String) this.getParameter("vocabulary");
		List<Vocabulary> vs = new VocabularyDao().queryByVocabulary(vocabulary.toLowerCase());
		if(vs.size()==0) {
			/*
			 * 嚐試query 完整大小寫字串
			 */
			 vs = new VocabularyDao().queryByVocabulary(vocabulary);
			 if(vs.size()==0) {
				 return this.SENDANDBACK_BROKEN;
			 }
		}
		this.setParameter("vocabularies", vs);
		this.dispatcher.send(MainView.queryResultFrame);
		return this.SENDANDBACK_NORMAL;
	}

}
