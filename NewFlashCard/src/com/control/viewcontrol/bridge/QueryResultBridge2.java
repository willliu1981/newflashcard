package com.control.viewcontrol.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.control.bridge.Bridge;
import com.control.dao.VocabularyDao;
import com.control.pad.PadFactory;
import com.model.Vocabulary;
import com.tool.Mask;
import com.view.MainView;
import com.view.QueryResultFrame;

public class QueryResultBridge2 extends Bridge {

	@Override
	public void doSend() {
	}

	@Override
	public Mask doSendAndBack() {
		String vocabulary = (String) this.getParameter("vocabulary");
		int limit;
		if (this.getParameter("limit") == null) {
			limit = 0;
		}
		limit = (int) this.getParameter("limit");

		VocabularyDao dao = new VocabularyDao();
		List<String> ss = null;
		if (limit == -1) {
			ss = dao.queryByFuzzyVocabulary(vocabulary);
		} else {
			ss = dao.queryByFuzzyVocabulary(vocabulary, limit);
		}
		
		if (ss.size() == 0) {
			return this.SENDANDBACK_BROKEN;
		}
		
		List<String> temp=new ArrayList<>();
		ss=ss.stream().sorted().filter(x->{
			boolean r=false;
			if(x.startsWith(vocabulary)) {
				temp.add(x);
			}else {
				r=true;
			}
			return r;
		}).collect(Collectors.toList());
		ss.addAll(temp);

		String v = ss.stream().filter(x -> x.startsWith(vocabulary)).findFirst().orElse(ss.get(0));
		List<Vocabulary> vs = dao.queryByVocabulary(v);
		this.setParameter("vocabulary", v);
		this.setParameter("fuzzyvocabularies", ss);
		
		vs = dao.queryByVocabulary(v);
		if (vs.size() == 0) {
			return this.SENDANDBACK_BROKEN;
		}

		this.setParameter("vocabularies", vs);
		this.dispatcher.send(MainView.queryResultFrame);
		return this.SENDANDBACK_NORMAL;
	}

}
