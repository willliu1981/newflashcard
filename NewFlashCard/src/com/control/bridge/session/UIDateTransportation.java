package com.control.bridge.session;

import java.util.HashMap;
import java.util.Map;

import com.control.bridge.Dispatcher;
import com.tool.Mask;

public abstract class UIDateTransportation<T> {

	public static class Session {
		private Map<String, Object> session = new HashMap<>();

		public void setAttribute(String name, Object obj) {
			this.session.put(name, obj);
		}

		public Object getAttribute(String name) {
			return this.session.get(name);
		}
	}

	public static final Mask SENDANDBACK_DEFAULT = new Mask(2);
	public static final Mask SENDANDBACK_BROKEN =  new Mask(4);
	public static final Mask SENDANDBACK_NORMAL =  new Mask(8);
	public static final Mask SENDANDBACK_INTERRUPT =  new Mask(16);//不進行下一步 send 或者 accept

	protected Dispatcher<T> dispatcher = new Dispatcher<T>(this);
	protected final Map<String, Object> parameter = new HashMap<>();

	static final Session session = new Session();

	public static Session getSession() {
		return session;
	}

	public Dispatcher<?> getDispatcher() {
		return this.dispatcher;
	}

	public abstract void doSend();

	public Mask doSendAndBack() {
		return SENDANDBACK_DEFAULT;
	}
	
	public T callback() {
		return null;
	}

	public void setParameter(String name, Object obj) {
		this.parameter.put(name, obj);
	}

	public Object getParameter(String name) {
		return this.parameter.get(name);
	}
}
