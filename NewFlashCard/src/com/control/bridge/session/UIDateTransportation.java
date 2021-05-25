package com.control.bridge.session;

import java.util.HashMap;
import java.util.Map;

import com.control.bridge.Dispatcher;

public abstract class UIDateTransportation {

	public static class Session {
		private Map<String, Object> session = new HashMap<>();

		public void setAttribute(String name, Object obj) {
			this.session.put(name, obj);
		}

		public Object getAttribute(String name) {
			return this.session.get(name);
		}
	}

	protected Dispatcher dispatcher = new Dispatcher(this);
	protected final Map<String, Object> parameter = new HashMap<>();

	static final Session session = new Session();

	public static Session getSession() {
		return session;
	}

	public Dispatcher getDispatcher() {
		return this.dispatcher;
	}

	public abstract void doSend();

	public void setParameter(String name, Object obj) {
		this.parameter.put(name, obj);
	}

	public Object getParameter(String name) {
		return this.parameter.get(name);
	}
}
