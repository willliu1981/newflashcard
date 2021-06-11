package com.control.viewcontrol.bridge;

import com.control.bridge.Bridge;
import com.model.Vocabulary;
import com.view.MainView;

public class OpenToAddVocabularyFrameBridge extends Bridge {

	@Override
	public void doSend() {
		Vocabulary vocabulary=(Vocabulary) this.getParameter("vocabulary");
		this.setParameter("vocabulary", vocabulary);
		this.setParameter("fetch", true);
		this.getDispatcher().send(MainView.addVocabularyFrame);
	}

}
