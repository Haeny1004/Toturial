package kr.sys4u.http.socket.server;

import java.io.IOException;
import java.net.Socket;

public class ClientRunner implements Runnable {

	private final Socket clientSocket;

	public ClientRunner(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	private void execute() {
		try {
			new HttpResponseCommander(clientSocket, new HttpRequestReader(clientSocket).read()).command();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		execute();
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
