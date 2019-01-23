package com.socket.exception;

public class StreamGetterException extends RuntimeException {

	private static final long serialVersionUID = -8080236485895903134L;

	public StreamGetterException() {
		super();
	}

	public StreamGetterException(String message, Throwable cause) {
		super(message, cause);
	}

	public StreamGetterException(String message) {
		super(message);
	}

	public StreamGetterException(Throwable cause) {
		super(cause);
	}
}
