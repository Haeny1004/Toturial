package kr.sys4u.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ChatRunner implements Runnable {

	private final List<ChatRunner> chatRunners;
	private final Socket clientSocket;
	private ClientInfo clientInfo;

	public ChatRunner(Socket clientSocket, List<ChatRunner> chatRunners) {
		this.clientSocket = clientSocket;
		this.chatRunners = chatRunners;
		this.clientInfo = new ClientInfo("0", null);
	}

	@Override
	public void run() {
		try (PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

			ServerParser serverParser = new ServerParser();
			while (true) {
				serverParser.parseMsg(in.readLine());
				String command = serverParser.getCommand();
				new ServerCommander(this).getTasker(command).task(serverParser.getContend());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getClientSocket() {
		return clientSocket;
	}
	
	public List<ChatRunner> getChatRunners(){
		return chatRunners;
	}
	
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
}
