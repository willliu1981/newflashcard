package com.control.bridge;

import com.control.bridge.session.UIDateTransportation;

public class Dispatcher {

	private UIDateTransportation dt;

	public Dispatcher(UIDateTransportation dt) {
		this.dt = dt;
	}

	public void send(UIDateTransportation bridge) {
		dt.doSend();
	}
	
	public void send(Transportable t) {
		t.dispatch(dt);
	}
}
