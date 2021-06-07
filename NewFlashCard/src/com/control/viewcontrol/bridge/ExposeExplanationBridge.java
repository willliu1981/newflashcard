package com.control.viewcontrol.bridge;

import java.util.Map;

import javax.swing.JOptionPane;

import com.control.bridge.Bridge;
import com.control.bridge.Dispatcher;
import com.control.pad.PadFactory;
import com.control.viewcontrol.ExplanationBridge;
import com.model.Vocabulary;
import com.tool.MyColor;
import com.view.MainView;

public class ExposeExplanationBridge extends Bridge {

	@Override
	public void doSend() {
		Vocabulary v =null;
		if((v=(Vocabulary) this.getParameter("correctVocabulary"))==null) {
			Session sess = this.getSession();
			Map<Integer, Vocabulary> map = (Map<Integer, Vocabulary>) sess.getAttribute("randomAnswers");
			v = map.get(this.getParameter("id"));
		}
		
		ExplanationBridge bridge = new ExplanationBridge();
		bridge.setParameter("vocabulary", v);
		Dispatcher dispater = bridge.getDispatcher();
		dispater.send();
	}

}
