package kr.sys4u.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kr.sys4u.server.room.Room;

public class ServerExecutor{

	private final List<ChatRunner> chatClients = new ArrayList<>();
	private final Map<String,Room> chatRooms = new HashMap<>();
	private final ExecutorService executorService = Executors.newFixedThreadPool(20);
	private final ServerSocket serverSocket;

	public ServerExecutor(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}
	
	public void execute() throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		System.out.println("[" + dateFormat.format(new Date()) + "][ Server 대기 상태 ]");
		while(true) {
			Socket clientSocket = serverSocket.accept();
			ChatRunner chatClient = new ChatRunner(clientSocket, chatClients, chatRooms); 
			chatClients.add(chatClient);
			executorService.execute(chatClient);
			System.out.println("[" + dateFormat.format(new Date()) + "][현재 접속자 수 : " + chatClients.size() + "명]");
		}
	}

	public void close() throws IOException {
		serverSocket.close();
	}
}
