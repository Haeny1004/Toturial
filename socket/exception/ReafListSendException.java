package com.socket.exception;

public class ReafListSendException extends RuntimeException {

	private static final long serialVersionUID = -1565930495886823604L;

	public ReafListSendException() {
		super();
	}

	public ReafListSendException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReafListSendException(String message) {
		super(message);
	}

	public ReafListSendException(Throwable cause) {
		super(cause);
	}
}
