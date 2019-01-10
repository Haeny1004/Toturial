package com.tutorial.generic.exception;

public class ConvertTreeException extends RuntimeException {

	private static final long serialVersionUID = 2705115578667498658L;

	public ConvertTreeException() {
		super();
	}

	public ConvertTreeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConvertTreeException(String message) {
		super(message);
	}

	public ConvertTreeException(Throwable cause) {
		super(cause);
	}

}
