package com.control.bridge;

import com.control.bridge.session.UIDateTransportation;
import com.tool.Mask;

public class Dispatcher <T>{

	private UIDateTransportation<T> dt;

	public Dispatcher(UIDateTransportation<T> dt) {
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
	public Mask sendAndBack(Transportable t) {
		Mask r=t.accpetThenBack(dt);
		if(r.has(dt.SENDANDBACK_INTERRUPT)) {
			return r;
		}
		t.accpet(dt);
		return r;
	}
	
	public T callback() {
		T o=dt.callback();
		sendAndBack();
		return o;
	}
	
	public T callback(Transportable<T> t) {
		T o=(T) t.acceptThenCallback(dt);
		sendAndBack(t);
		return o;
	}
}
