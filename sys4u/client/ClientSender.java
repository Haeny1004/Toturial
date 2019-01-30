package kr.sys4u.client;

import java.io.PrintWriter;

public class ClientSender{

	private final PrintWriter out;
	
	public ClientSender(PrintWriter out) {
		this.out = out;
	}
	
	public void send(String message) {
		if(message.isEmpty()) {
			return;
		}
		out.println(message);
		out.flush();
	}
	
}
