package kr.sys4u.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProcessor{

	private final Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public ClientProcessor(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	private void init() throws IOException {
		out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	public void process() {
		try {
			init();
		} catch (IOException e) {
			disConnect();
		}
		new Thread(new InputProcessor(in)).start();
		new Thread(new OutputProcessor(out)).start();
	}
	
	private void disConnect() {
		out.flush();
		out.close();
		try {
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
