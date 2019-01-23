package com.socket.exception;

public class FileDataReceiveAndGenerateException extends RuntimeException {

	private static final long serialVersionUID = -6363374793793584158L;

	public FileDataReceiveAndGenerateException() {
		super();
	}

	public FileDataReceiveAndGenerateException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileDataReceiveAndGenerateException(String message) {
		super(message);
	}

	public FileDataReceiveAndGenerateException(Throwable cause) {
		super(cause);
	}
}
