package kr.sys4u.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerProcessor {

	protected static List<UserThread> userThreads = new ArrayList<>();
	private static ExecutorService executor = Executors.newFixedThreadPool(20);
	private final ServerSocket serverSocket;
	
	public ServerProcessor(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public void process() throws IOException {
		System.out.println("[" + System.currentTimeMillis() + "][ Server 대기 상태 ]");
		while(true) {
			Socket clientSocket = serverSocket.accept();
			UserThread userThread = new UserThread(clientSocket); 
			userThreads.add(userThread);
			executor.execute(userThread);
			System.out.println("[" + System.currentTimeMillis() + "][현재 접속자 수 : " + userThreads.size() + "명]");
		}
	}
}
