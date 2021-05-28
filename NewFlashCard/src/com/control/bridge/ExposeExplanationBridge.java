package com.control.bridge;

import java.util.Map;

import com.control.bridge.session.UIDateTransportation;
import com.model.Vocabulary;
import com.view.MainView;

public class ExposeExplanationBridge extends Bridge {

	@Override
	public void doSend(UIDateTransportation dt) {
		Session sess=this.getSession();
		Map<Integer,Vocabulary> map=(Map<Integer, Vocabulary>) sess.getAttribute("randomAnswers");
		Vocabulary v=map.get(this.getParameter("id"));
		this.setParameter("vocabulary", v);
		this.getDispatcher().send(MainView.explantationFrame);
	}

}
