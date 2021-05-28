package com.control.bridge;

import com.control.bridge.session.UIDateTransportation;

public class Dispatcher {

	private UIDateTransportation dt;

	public Dispatcher(UIDateTransportation dt) {
		this.dt = dt;
	}

	public void send(UIDateTransportation dt) {
		this.dt.doSend(dt);
	}
	
	public void send(Transportable t) {
		t.dispatch(dt);
	}
	
	public int sendAndBack(UIDateTransportation dt) {
		int r=this.dt.doSendAndBack(dt);
		this.dt.doSend(dt);
		return r;
	}
}
