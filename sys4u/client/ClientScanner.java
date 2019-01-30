package kr.sys4u.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientScanner implements Runnable{

	private final Socket clientSocket;
	private final PrintWriter out;
	private final Scanner scan;
	private ClientSender clientSender;
	
	public ClientScanner(PrintWriter out, Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.out = out;
		this.scan = new Scanner(System.in);
	}
	
	private void init() {
		clientSender = new ClientSender(out);
	}
	
	@Override
	public void run() {
		init();
		while(true) {
			String parsedMessage = new ClientParser().parseMessage(scan.nextLine());
			if(parsedMessage.equals("QUIT|")) {
				try {
					clientSocket.close();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			clientSender.send(parsedMessage);
		}
	}

}

	
