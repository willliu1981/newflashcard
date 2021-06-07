package com.control.exception;

import java.io.IOException;

public class MyNullException extends IOException {
	public MyNullException() {
		super("null");
	}
	public MyNullException(String msg) {
		super(msg);
	}
}
