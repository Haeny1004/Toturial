package com.tutorial.socket.exception;

public class WriteObjectException extends RuntimeException {
	private static final long serialVersionUID = -4084672347433649562L;

	public WriteObjectException() {
		super();
	}

	public WriteObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public WriteObjectException(String message) {
		super(message);
	}

	public WriteObjectException(Throwable cause) {
		super(cause);
	}
}
