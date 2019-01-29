package kr.sys4u.client;

import java.io.PrintWriter;
import java.util.Scanner;

public class ClientScanner implements Runnable{

	private final PrintWriter out;
	private final Scanner scan;
	
	private ClientSender clientSender;
	
	public ClientScanner(PrintWriter out) {
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
		}
	}

}

	
