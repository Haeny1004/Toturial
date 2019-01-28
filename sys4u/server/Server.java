package kr.sys4u.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Closeable{
	private final int port;
	
	private boolean initialized = false;
	private ServerSocket serverSocket;
	
	public Server(int port) {
		this.port = port;
	}
	
	private void init() throws IOException {
		if(initialized) {
			return;
		}
		serverSocket = new ServerSocket(port);
		initialized = true;
	}
	
	private void execute() throws IOException {
		if(!initialized) {
			init();
		}
		new ServerProcessor(serverSocket).process();
	}
	
	public void close() {
		if(!initialized) {
			return;
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace(); //처리필요
		}
	}
	
	public static void main(String[] args) {
		try(Server server = new Server(8888)){
			server.init();
			server.execute();
		} catch (IOException e) {
			e.printStackTrace(); //처리필요
		}
	}

} // End of class
