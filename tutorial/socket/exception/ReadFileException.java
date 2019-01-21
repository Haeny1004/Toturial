package com.tutorial.socket.exception;

public class ReadFileException extends RuntimeException {

	private static final long serialVersionUID = -3240353688970333411L;

	public ReadFileException() {
		super();
	}

	public ReadFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReadFileException(String message) {
		super(message);
	}

	public ReadFileException(Throwable cause) {
		super(cause);
	}
}
