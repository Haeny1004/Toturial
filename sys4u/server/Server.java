package kr.sys4u.server;

import java.io.Closeable;
import java.io.IOException;

public class Server implements Closeable{

	private final int port;
	private boolean initialized = false;
	private ServerExecutor serverExecutor;

	public Server(int port) {
		this.port = port;
	}

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
	}
	
	@Override
	public void close() {
		try {
			serverExecutor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try(Server server = new Server(8888)) {
			server.init();
			server.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


} // End of class
