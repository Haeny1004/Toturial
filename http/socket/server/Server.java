package kr.sys4u.http.socket.server;

import java.io.Closeable;
import java.io.IOException;

public class Server implements Closeable {

	private final int port = 8888;
	private ServerExecutor serverExecutor;
	private boolean initialized = false;

	private void init() throws IOException {
		if (initialized) {
			return;
		}
		serverExecutor = new ServerExecutor(port);
		initialized = true;
	}

	private void execute() throws IOException {
		if (!initialized) {
			init();
		}
		serverExecutor.execute();
//		while(true) {
//			Socket clientSocket = serverSocket.accept();
//			executor.execute(new ClientRunner(clientSocket));
//		}
	}

	@Override
	public void close(){
		if (!initialized) {
			return;
		}
		serverExecutor.close();
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
