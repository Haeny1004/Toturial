package com.tutorial.generic.exception;

public class StreamerCloseException extends RuntimeException {

	private static final long serialVersionUID = 5408826159472383893L;

	public StreamerCloseException() {
		super();
	}

	public StreamerCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public StreamerCloseException(String message) {
		super(message);
	}

	public StreamerCloseException(Throwable cause) {
		super(cause);
	}
}
