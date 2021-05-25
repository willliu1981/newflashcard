package com.control.bridge;

import com.view.MainView;

public class ExposeExplanationBridge extends Bridge {

	@Override
	public void doSend() {
		this.getDispatcher().send(MainView.testQuestionExposition);
	}

}
