package com.socket.exception;

public class FileDataReadAndSendException extends RuntimeException {

	private static final long serialVersionUID = 3757289521940122082L;

	public FileDataReadAndSendException() {
		super();
	}

	public FileDataReadAndSendException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileDataReadAndSendException(String message) {
		super(message);
	}

	public FileDataReadAndSendException(Throwable cause) {
		super(cause);
	}
}
