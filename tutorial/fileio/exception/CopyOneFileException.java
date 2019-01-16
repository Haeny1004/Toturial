package com.tutorial.fileio.exception;

public class CopyOneFileException extends RuntimeException {

	private static final long serialVersionUID = 9070811719415529801L;

	public CopyOneFileException() {
		super();
	}

	public CopyOneFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public CopyOneFileException(String message) {
		super(message);
	}

	public CopyOneFileException(Throwable cause) {
		super(cause);
	}
}
