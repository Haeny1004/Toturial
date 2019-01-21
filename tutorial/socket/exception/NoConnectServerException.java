package com.tutorial.socket.exception;

public class NoConnectServerException extends RuntimeException {

	private static final long serialVersionUID = -4094548029172749343L;

	public NoConnectServerException() {
		super();
	}

	public NoConnectServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoConnectServerException(String message) {
		super(message);
	}

	public NoConnectServerException(Throwable cause) {
		super(cause);
	}
}
