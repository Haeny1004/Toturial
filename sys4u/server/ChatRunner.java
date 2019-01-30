package kr.sys4u.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
		try (PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

			while (true) {
				ServerParser serverParser = new ServerParser();
				serverParser.parseMsg(in.readLine());
				String command = serverParser.getCommand();
				new ServerCommander(this).getTasker(command).task(serverParser.getContent());
			}
		} catch (IOException e) {
			disConnect();
		} catch (IllegalArgumentException e) {
			disConnect();
		}
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

	public void disConnect() {
		try {
			clientSocket.close();
		} catch (IOException e) {
		}
		chatRunners.remove(this);
		new ServerSender(this).sendMessageToAll(clientInfo.getName() + "님이 퇴장하셨습니다.");
		System.out.println("[" + new SimpleDateFormat("hh:mm:ss").format(new Date()) + "][현재 접속자 수 : " + chatRunners.size() + "명]");
	}

}
