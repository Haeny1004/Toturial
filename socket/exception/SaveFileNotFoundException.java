package com.socket.exception;

public class SaveFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8224537201875933093L;

	public SaveFileNotFoundException() {
		super();
	}

	public SaveFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SaveFileNotFoundException(String message) {
		super(message);
	}

	public SaveFileNotFoundException(Throwable cause) {
		super(cause);
	}
}
