package kr.sys4u.http.socket.server;

public class StartLine {

	private String method;
	private String path;
	private String version;
	
	public StartLine(String method, String path, String version) {
		super();
		this.method = method;
		this.path = path;
		this.version = version;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
