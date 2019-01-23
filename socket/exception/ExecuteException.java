package com.socket.exception;

public class ExecuteException extends RuntimeException {

	private static final long serialVersionUID = 6902847462239468951L;

	public ExecuteException() {
		super();
	}

	public ExecuteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExecuteException(String message) {
		super(message);
	}

	public ExecuteException(Throwable cause) {
		super(cause);
	}
}
