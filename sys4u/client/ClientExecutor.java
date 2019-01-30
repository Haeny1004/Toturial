package kr.sys4u.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientExecutor{

	private final ExecutorService executor = Executors.newFixedThreadPool(2);
	private final Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public ClientExecutor(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	private void init() throws IOException {
		out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	public void execute(String clientName) {
		try {
			init();
		} catch (IOException e) {
			disConnect();
		}
		new ClientSender(out).send(clientName);
		executor.execute(new ClientReceiver(in));
		executor.execute(new ClientScanner(out, clientSocket));
		executor.shutdown();
	}
	
	private void disConnect() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
	
}
