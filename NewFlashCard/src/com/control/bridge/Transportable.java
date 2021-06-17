package com.control.bridge;

import com.control.bridge.session.UIDateTransportation;
import com.tool.Mask;

public interface Transportable {
	public UIDateTransportation accpet(UIDateTransportation dt);
	
	public void setUIDateTransportation(UIDateTransportation dt);
	
	public default void dispatch(UIDateTransportation dt) {
		UIDateTransportation _dt =this.accpet(dt);
		this.setUIDateTransportation(_dt);
	}
	
	public default Mask dispatchThenBack(UIDateTransportation dt) {
		return this.accpetThenBack(dt);
	}
	
	public default Mask accpetThenBack(UIDateTransportation dt) {
		return dt.SENDANDBACK_DEFAULT;
	}
}
