package kr.sys4u.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import kr.sys4u.server.room.Room;

public class ChatRunner implements Runnable {

	private final List<ChatRunner> chatRunners;
	private final Map<String ,Room> chatRooms;
	private final Socket clientSocket;
	private ClientInfo clientInfo;

	public ChatRunner(Socket clientSocket, List<ChatRunner> chatRunners, Map<String, Room> chatRooms) {
		this.clientSocket = clientSocket;
		this.chatRunners = chatRunners;
		this.chatRooms = chatRooms;
		this.clientInfo = new ClientInfo("0", null);
	}

	@Override
	public void run() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			while (true) {
				processingMessage(in);
			}
		} catch (IOException | IllegalArgumentException e) {
			disConnect();
		}
	}

	private void processingMessage(BufferedReader in) throws IOException {
		ServerParser serverParser = new ServerParser();
		serverParser.parseMsg(in.readLine());
		new ServerCommander(this).getTasker(serverParser.getCommand()).task(serverParser.getContent());
	}
	
	private void disConnect() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			//ignore
		}
		chatRunners.remove(this);
		new ServerSender(this).sendMessageToAll(clientInfo.getName() + "님이 퇴장하셨습니다.");
		System.out.println("[" + new SimpleDateFormat("hh:mm:ss").format(new Date()) 
								+ "][현재 접속자 수 : " + chatRunners.size() + "명]");
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public List<ChatRunner> getChatRunners() {
		return chatRunners;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public Map<String, Room> getChatRooms() {
		return chatRooms;
	}

}
