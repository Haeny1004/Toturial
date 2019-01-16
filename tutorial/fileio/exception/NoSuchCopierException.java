package com.tutorial.fileio.exception;

public class NoSuchCopierException extends RuntimeException {

	private static final long serialVersionUID = 5689787864291652877L;

	public NoSuchCopierException() {
		super();
	}

	public NoSuchCopierException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchCopierException(String message) {
		super(message);
	}

	public NoSuchCopierException(Throwable cause) {
		super(cause);
	}
}
