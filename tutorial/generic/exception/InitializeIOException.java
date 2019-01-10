package com.tutorial.generic.exception;

public class InitializeIOException extends RuntimeException {

	private static final long serialVersionUID = 6841439934311798146L;

	public InitializeIOException() {
		super();
	}

	public InitializeIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitializeIOException(String message) {
		super(message);
	}

	public InitializeIOException(Throwable cause) {
		super(cause);
	}
}
