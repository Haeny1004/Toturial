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

	private final List<ChatRunner> chatRunners = new ArrayList<>();
	private final Map<String,Room> chatRooms = new HashMap<>();
	private final ExecutorService executorService = Executors.newFixedThreadPool(20);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
	private final ServerSocket serverSocket;

	public ServerExecutor(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}
	
	public void execute() throws IOException {
		System.out.println("[" + dateFormat.format(new Date()) + "][ Server 대기 상태 ]");
		while(true) {
			Socket clientSocket = serverSocket.accept();
			addAndExecuteChatRunner(clientSocket);
			System.out.println("[" + dateFormat.format(new Date()) + "][현재 접속자 수 : " + chatRunners.size() + "명]");
		}
	}

	private void addAndExecuteChatRunner(Socket clientSocket) {
		ChatRunner chatRunner = new ChatRunner(clientSocket, chatRunners, chatRooms);
		chatRunners.add(chatRunner);
		executorService.execute(chatRunner);
	}

	public void close() throws IOException {
		serverSocket.close();
	}
}
