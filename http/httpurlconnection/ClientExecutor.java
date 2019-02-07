package kr.sys4u.http.httpurlconnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ClientExecutor {

	private final HttpURLConnection httpUrlConn;
	private BufferedWriter out;
	private BufferedReader in;
	private boolean initialized = false;

	public ClientExecutor(HttpURLConnection httpUrlConnection) {
		httpUrlConn = httpUrlConnection;
	}

	private void init() {
		if (initialized) {
			return;
		}
		try {
			in = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialized = true;
	}

	public void execute() {
		if (!initialized) {
			init();
		}
		try {
//			sendRequest();
			readResponse();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendRequest() throws IOException {
		out.write("GET / HTTP/1.1\\r\\n");
		out.flush();
		System.out.println("Request Success!");
	}

	private void readResponse() throws IOException {
		String readLine = null;
		while (true) {
			if ((readLine = in.readLine()) == null) {
				httpUrlConn.disconnect();
				return;
			}
			System.out.println(readLine);
		}
	}
}
