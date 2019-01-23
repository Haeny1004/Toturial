package com.socket.exception;

public class ServerCloseException extends RuntimeException {

	private static final long serialVersionUID = 2018566531606706250L;

	public ServerCloseException() {
		super();
	}

	public ServerCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerCloseException(String message) {
		super(message);
	}

	public ServerCloseException(Throwable cause) {
		super(cause);
	}
}
