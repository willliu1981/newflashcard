package com.control.bridge;

import java.util.HashMap;
import java.util.Map;

import com.control.bridge.session.UIDateTransportation;

public abstract class Bridge extends UIDateTransportation{
	


	private final  Map<String ,Object> parameter=new HashMap<>();
	
	public void setParameter(String name,Object obj) {
		this.parameter.put(name, obj);
	}
	
	public Object getParameter(String name) {
		return this.parameter.get(name);
	}
	
	
	
	
}
