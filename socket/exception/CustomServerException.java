package com.socket.exception;

public class CustomServerException extends RuntimeException {

	private static final long serialVersionUID = 1316263258698306195L;

	public CustomServerException() {
		super();
	}

	public CustomServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomServerException(String message) {
		super(message);
	}

	public CustomServerException(Throwable cause) {
		super(cause);
	}
}
