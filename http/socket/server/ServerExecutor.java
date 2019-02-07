package kr.sys4u.http.socket.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExecutor implements Closeable{

	private final ExecutorService executor = Executors.newFixedThreadPool(20);
	private ServerSocket serverSocket;
	private boolean initialized = false;

	public ServerExecutor(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
	}
	
	private void init() {
		if(initialized) {
			return;
		}
		
		
		initialized = true; 
	}
	
	public void execute() {
		
	}

	@Override
	public void close(){
		try {
			serverSocket.close();
		} catch (IOException e) {

		}
	}

}
