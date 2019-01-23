package com.socket.exception;

public class TargetFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5835799840875591879L;

	public TargetFileNotFoundException() {
		super();
	}

	public TargetFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TargetFileNotFoundException(String message) {
		super(message);
	}

	public TargetFileNotFoundException(Throwable cause) {
		super(cause);
	}
}
