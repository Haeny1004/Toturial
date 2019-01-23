package com.socket.exception;

public class HostConnectException extends RuntimeException {

	private static final long serialVersionUID = 5130996162405818752L;

	public HostConnectException() {
		super();
	}

	public HostConnectException(String message, Throwable cause) {
		super(message, cause);
	}

	public HostConnectException(String message) {
		super(message);
	}

	public HostConnectException(Throwable cause) {
		super(cause);
	}
}
