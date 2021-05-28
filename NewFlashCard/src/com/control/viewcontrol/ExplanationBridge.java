package com.control.viewcontrol;

import com.control.bridge.Bridge;
import com.control.bridge.Dispatcher;
import com.control.bridge.session.UIDateTransportation;
import com.view.MainView;

public class ExplanationBridge extends  Bridge{

	@Override
	public void doSend() {
		 Dispatcher disp=this.getDispatcher();
		 disp.send(MainView.explantationFrame);
	}
	
}
