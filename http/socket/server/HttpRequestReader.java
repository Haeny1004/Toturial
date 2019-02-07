package kr.sys4u.http.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpRequestReader {

	private final Socket clientSocket;
	private BufferedReader in;
	private boolean initialized = false;
	
	public HttpRequestReader(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	private void init() {
		if(initialized) {
			return;
		}
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialized = true;
	}
	
	public StartLine read() throws IOException {
		if(!initialized) {
			init();
		}
		String readLine = in.readLine();
		StringTokenizer tokenizer = new StringTokenizer(readLine, " ");
		StartLine startLine = new StartLine(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
		System.out.println("Reading Complete!");
		return startLine;
	}
	
	
}
