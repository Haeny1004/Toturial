package kr.sys4u.http.socket.server;

import java.io.IOException;
import java.net.Socket;

public class ServerExecutor implements Runnable {

	private final Socket clientSocket;

	public ServerExecutor(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	private void execute() {
		try {
			new HttpResponseSender(clientSocket, new HttpRequestReader(clientSocket).read()).send();
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
