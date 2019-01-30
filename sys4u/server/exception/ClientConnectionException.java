package kr.sys4u.server.exception;

public class ClientConnectionException extends RuntimeException {

	private static final long serialVersionUID = 4094328909762261377L;

	public ClientConnectionException() {
		super();
	}

	public ClientConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientConnectionException(String message) {
		super(message);
	}

	public ClientConnectionException(Throwable cause) {
		super(cause);
	}
	
}
