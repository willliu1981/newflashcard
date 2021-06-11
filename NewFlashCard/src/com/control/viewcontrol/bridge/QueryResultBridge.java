package com.control.viewcontrol.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.control.bridge.Bridge;
import com.control.dao.VocabularyDao;
import com.control.pad.PadFactory;
import com.model.Vocabulary;
import com.tool.Mask;
import com.view.MainView;
import com.view.QueryResultFrame;

public class QueryResultBridge extends Bridge {

	@Override
	public void doSend() {
	}

	@Override
	public Mask doSendAndBack() {
		String vocabulary = (String) this.getParameter("vocabulary");
		Mask type = (Mask) this.getParameter("type");
		int limit = (int) this.getParameter("limit");

		VocabularyDao dao = new VocabularyDao();
		List<Vocabulary> vs = null;
		if (type.has(PadFactory.SEARCH_FUZZY)) {
			List<String> ss = null;
			if (limit == -1) {
				ss = dao.queryByFuzzyVocabulary(vocabulary);
			} else {
				ss = dao.queryByFuzzyVocabulary(vocabulary, limit);
			}

			if (ss.size() == 0) {
				return this.SENDANDBACK_BROKEN;
			}
			String v=null;
			vs = dao.queryByVocabulary((v=ss.stream().filter(x -> x.startsWith(vocabulary)).findFirst().orElse(ss.get(0))));
			this.setParameter("vocabulary", v);
			this.setParameter("fuzzyvocabularies", ss);
		} else if (type.has(PadFactory.SEARCH_EXACTLY)) {
			vs = dao.queryByVocabulary(vocabulary);
			if (vs.size() == 0) {
				return this.SENDANDBACK_BROKEN;
			}
		}

		this.setParameter("vocabularies", vs);
		this.dispatcher.send(MainView.queryResultFrame);
		return this.SENDANDBACK_NORMAL;
	}

}
