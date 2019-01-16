package com.tutorial.fileio.exception;

public class ConversionException extends RuntimeException {

	private static final long serialVersionUID = -5709860762521065635L;

	public ConversionException() {
		super();
	}

	public ConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConversionException(String message) {
		super(message);
	}

	public ConversionException(Throwable cause) {
		super(cause);
	}

}
