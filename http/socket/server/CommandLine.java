package kr.sys4u.http.socket.server;

public class CommandLine {

	private String method;
	private String url;
	private String extension;
	private String version;

	public CommandLine(String method, String url, String extension, String version) {
		super();
		this.method = method;
		this.url = url;
		this.extension = extension;
		this.version = version;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
