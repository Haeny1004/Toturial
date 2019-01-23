package com.socket.exception;

public class ReafFileListReadException extends RuntimeException {

	private static final long serialVersionUID = -7252606799441042444L;

	public ReafFileListReadException() {
		super();
	}

	public ReafFileListReadException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReafFileListReadException(String message) {
		super(message);
	}

	public ReafFileListReadException(Throwable cause) {
		super(cause);
	}
}
