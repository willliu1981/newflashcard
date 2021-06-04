package com.control.bridge;

import com.control.bridge.session.UIDateTransportation;
import com.tool.Mask;

public class Dispatcher {

	private UIDateTransportation dt;

	public Dispatcher(UIDateTransportation dt) {
		this.dt = dt;
	}

	public void send() {
		this.dt.doSend();
	}
	
	public void send(Transportable t) {
		t.dispatch(dt);
	}
	
	public Mask sendAndBack() {
		Mask r=this.dt.doSendAndBack();
		if(r.has(dt.SENDANDBACK_INTERRUPT)) {
			return r;
		}
		this.dt.doSend();
		return r;
	}
}
