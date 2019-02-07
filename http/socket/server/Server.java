package kr.sys4u.http.socket.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Closeable {

	private final int port = 8888;
	private final ExecutorService executor = Executors.newFixedThreadPool(20);
	private ServerSocket serverSocket;
	private boolean initialized = false;

	private void init() throws IOException {
		if (initialized) {
			return;
		}
		serverSocket = new ServerSocket(port);
		initialized = true;
	}

	private void execute() throws IOException {
		if (!initialized) {
			init();
		}
		while(true) {
			Socket clientSocket = serverSocket.accept();
			executor.execute(new ServerExecutor(clientSocket));
		}
	}

	@Override
	public void close(){
		if (!initialized) {
			return;
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try (Server server = new Server()) {
			server.init();
			server.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
