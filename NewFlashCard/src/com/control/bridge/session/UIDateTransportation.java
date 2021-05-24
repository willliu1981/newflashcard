package com.control.bridge.session;

import java.util.HashMap;
import java.util.Map;

import com.control.bridge.Dispatcher;

public abstract class UIDateTransportation {
	protected Dispatcher dispatcher =new Dispatcher();
	

	
	static class Session {
		private Map<String, Object> session = new HashMap<>();

		public void setAttribute(String name, Object obj) {
			this.session.put(name, obj);
		}

		public Object getAttribute(String name) {
			return this.session.get(name);
		}
	}

	static final Session session = new Session();

	static Session getSession() {
		return session;
	}
	
	public Dispatcher getDispatcher() {
		return this.dispatcher;
	}
}
