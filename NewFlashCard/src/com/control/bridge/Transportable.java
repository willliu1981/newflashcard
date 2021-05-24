package com.control.bridge;

import com.control.bridge.session.UIDateTransportation;

public interface Transportable {
	public void accpet();
	public void setUIDateTransportation(UIDateTransportation dt);
	
	public default void dispatch(UIDateTransportation dt) {
		this.setUIDateTransportation(dt);
		this.accpet();
	}
	
}
