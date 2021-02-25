package com.control.viewcontrol;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ShowRowInfo {
	protected Map<String, Component> comps = new HashMap<>();


	public void add(String name, Component comp) {
		comps.put(name, comp);
	}

	public abstract void showInfo(Map<String,String> map);

}
