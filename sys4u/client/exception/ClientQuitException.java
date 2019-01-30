package kr.sys4u.client.exception;

public class ClientQuitException extends RuntimeException {

	private static final long serialVersionUID = 723213970974798238L;

	public ClientQuitException() {
		super();
	}

	public ClientQuitException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientQuitException(String message) {
		super(message);
	}

	public ClientQuitException(Throwable cause) {
		super(cause);
	}
	
}
