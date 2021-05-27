package com.control.bridge;

import com.control.bridge.session.UIDateTransportation;

public interface Transportable {
	public UIDateTransportation accpet(UIDateTransportation dt);
	public void setUIDateTransportation(UIDateTransportation dt);
	
	public default void dispatch(UIDateTransportation dt) {
		UIDateTransportation _dt =this.accpet(dt);
		this.setUIDateTransportation(_dt);
	}
	
}
