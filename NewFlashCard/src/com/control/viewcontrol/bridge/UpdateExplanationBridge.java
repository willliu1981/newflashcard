package com.control.viewcontrol.bridge;

import com.control.bridge.Bridge;
import com.control.dao.VocabularyDao;
import com.model.Vocabulary;

public class UpdateExplanationBridge extends Bridge {

	@Override
	public void doSend() {
		VocabularyDao dao=new VocabularyDao();
		Vocabulary voca=(Vocabulary) this.getParameter("vocabulary");
		dao.update(voca, voca.getId());
	}

}
