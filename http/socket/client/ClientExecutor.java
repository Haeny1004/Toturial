package kr.sys4u.http.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientExecutor {

	private final Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private boolean initialized = false;

	public ClientExecutor(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	private void init() {
		if (initialized) {
			return;
		}
		try {
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialized = true;
	}

	public void execute() {
		if (!initialized) {
			init();
		}

		sendRequest();
		try {
			readResponse();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void sendRequest() {
		out.println("GET / HTTP/1.1");
		out.println("Host : www.naver.com");
		out.println("User-Agent: Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
		out.println("");
		out.flush();
		System.out.println("Request Success");
	}

	private void readResponse() throws IOException {
		System.out.println("[ Response Message ]");
		String readLine = null;
		while (true) {
			if ((readLine = in.readLine()) != null) {
				System.out.println(readLine);
			}
		}
	}

}
