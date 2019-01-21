package com.tutorial.socket.exception;

public class InvalidIpException extends RuntimeException {

	private static final long serialVersionUID = 1318356512501995316L;

	public InvalidIpException() {
		super();
	}

	public InvalidIpException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidIpException(String message) {
		super(message);
	}

	public InvalidIpException(Throwable cause) {
		super(cause);
	}
}
