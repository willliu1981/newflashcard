package com.control.bridge;

import com.control.bridge.session.UIDateTransportation;
import com.tool.Mask;

public interface Transportable {
	public void accpet(UIDateTransportation dt);
	
	
	public default void dispatch(UIDateTransportation dt) {
		this.accpet(dt);
	}
	
	public default Mask dispatchThenBack(UIDateTransportation dt) {
		return this.accpetThenBack(dt);
	}
	
	public default Mask accpetThenBack(UIDateTransportation dt) {
		return dt.SENDANDBACK_DEFAULT;
	}
}
