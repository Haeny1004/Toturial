package com.tutorial.socket.exception;

public class ConnectServerException extends RuntimeException {

	private static final long serialVersionUID = 2579102384576086984L;

	public ConnectServerException() {
		super();
	}

	public ConnectServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectServerException(String message) {
		super(message);
	}

	public ConnectServerException(Throwable cause) {
		super(cause);
	}
}
