package com.tca.exception;

public class AppException extends Exception {
	private static final long serialVersionUID = 1L;

	public AppException(String message) {
		super(message);
	}
	
	public AppException(String message, Throwable exception) {
		super(message, exception);
	}
	
}
