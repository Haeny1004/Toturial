package kr.sys4u.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;

public class UserThread implements Runnable{
	
	private final Socket clientSocket;
	private final List<UserThread> userThreads;
	private PrintWriter out;
	private BufferedReader in;
	
	public UserThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.userThreads = ServerProcessor.userThreads;
	}
	
	@Override
	public void run() {
		try{
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			while(true) {
				String msg = in.readLine();
				messageProcess(msg);
			}
		} catch (IOException e) {
			disConnect();
		}
	}
	
	private void messageProcess(String msg) {
		StringTokenizer token = new StringTokenizer(msg, "|");
		String command = token.nextToken();
		String talk = token.nextToken();
		
		if(command.equals("TALK")) {
			sendMessageToAll(talk);
		}
	}
	
	private void sendMessageToAll(String talk) {
		synchronized(userThreads) {
			for(UserThread userThread : userThreads) {
				userThread.sendMessage(talk);
			}
		}
	}

	private void sendMessage(String talk) {
		out.println(talk);
		out.flush();
	}
	
	private void disConnect() {
		try {
			out.flush();
			out.close();
			in.close();
			clientSocket.close();
			userThreads.remove(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
