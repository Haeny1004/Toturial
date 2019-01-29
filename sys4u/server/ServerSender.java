package kr.sys4u.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerSender {

	private final List<ChatRunner> chatRunners;
	private Socket clientSocket;
	private PrintWriter out;

	public ServerSender(List<ChatRunner> chatRunners) {
		this.chatRunners = chatRunners;
	}

	private void init(ChatRunner chatRunner) {
		clientSocket = chatRunner.getClientSocket();
		try {
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageToAll(String content) {
		synchronized (chatRunners) {
			for (ChatRunner chatRunner : chatRunners) {
				init(chatRunner);
				sendMessage(content);
			}
		}
	}

	private void sendMessage(String content) {
		out.println(content);
		out.flush();
	}

}
