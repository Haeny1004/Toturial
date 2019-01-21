package com.tutorial.socket.exception;

public class ReadObjectException extends RuntimeException {
	private static final long serialVersionUID = 3413783675773105458L;

	public ReadObjectException() {
		super();
	}

	public ReadObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReadObjectException(String message) {
		super(message);
	}

	public ReadObjectException(Throwable cause) {
		super(cause);
	}
}
