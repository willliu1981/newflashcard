package com.control.viewcontrol.bridge;

import java.util.ArrayList;
import java.util.List;

import com.control.bridge.Bridge;
import com.control.dao.VocabularyDao;
import com.control.pad.PadFactory;
import com.model.Vocabulary;
import com.tool.Mask;
import com.view.MainView;

public class QueryResultBridge extends Bridge {

	@Override
	public void doSend() {
	}

	@Override
	public Mask doSendAndBack() {
		String vocabulary = (String) this.getParameter("vocabulary");
		String type = (String) this.getParameter("type");
		int limit = (int) this.getParameter("limit");

		VocabularyDao dao = new VocabularyDao();
		List<String> ss = null;
		if (type.equals(PadFactory.SERCH_FUZZY_SERCH)) {
			if (limit == -1) {
				ss = dao.queryByFuzzyVocabulary(vocabulary);
			} else {
				ss = dao.queryByFuzzyVocabulary(vocabulary, limit);
			}
			if (ss.size() == 0) {
				return this.SENDANDBACK_BROKEN;
			}
			this.setParameter("fuzzyvocabularies", ss);
		} else if (type.equals(PadFactory.SERCH_EXACTLY_MATCHING)) {
			ss=new ArrayList<>();
			ss.add(vocabulary);
		}

		List<Vocabulary> vs = dao
				.queryByVocabulary(ss.stream().filter(x -> x.startsWith(vocabulary)).findFirst().get());
		if (vs.size() == 0) {
			return this.SENDANDBACK_BROKEN;
		}
		this.setParameter("vocabularies", vs);

		this.dispatcher.send(MainView.queryResultFrame);
		return this.SENDANDBACK_NORMAL;
	}

}
