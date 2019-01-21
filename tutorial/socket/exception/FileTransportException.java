package com.tutorial.socket.exception;

public class FileTransportException extends RuntimeException {

	private static final long serialVersionUID = 6776337556992879718L;

	public FileTransportException() {
		super();
	}

	public FileTransportException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileTransportException(String message) {
		super(message);
	}

	public FileTransportException(Throwable cause) {
		super(cause);
	}
	
}
