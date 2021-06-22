package com.control.bridge;

import com.control.bridge.session.UIDateTransportation;
import com.tool.Mask;

public interface Transportable <T>{
	public void accpet(UIDateTransportation<T> dt);
	
	
	public default void dispatch(UIDateTransportation<T> dt) {
		this.accpet(dt);
	}
	
	public default Mask dispatchThenBack(UIDateTransportation<T> dt) {
		return this.accpetThenBack(dt);
	}
	
	public default Mask accpetThenBack(UIDateTransportation<T> dt) {
		return dt.SENDANDBACK_DEFAULT;
	}
	
	public default Object acceptThenCallback(UIDateTransportation<T> dt) {
		return null;
	}
}
