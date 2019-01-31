package kr.sys4u.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.net.Socket;

public class ServerSender {

	private final ChatRunner chatRunner;
	private Socket clientSocket;
	private PrintWriter out;

	public ServerSender(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}

	private void init(ChatRunner chatRunner) {
		clientSocket = chatRunner.getClientSocket();
		try {
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public void sendMessageToAll(String content) {
		synchronized (chatRunner.getChatRunners()) {
			for (ChatRunner chatRunner : chatRunner.getChatRunners()) {
				if(!this.chatRunner.getClientInfo().getRoomTitle().equalsIgnoreCase(chatRunner.getClientInfo().getRoomTitle())) {
					continue;
				}
				init(chatRunner);
				sendMessage(content);
			}
		}
	}
	
	public void sendMessageToMe(String content) {
		init(chatRunner);
		sendMessage(content);
	}
	
	private void sendMessage(String content) {
		out.println(content);
		out.flush();
	}

}
