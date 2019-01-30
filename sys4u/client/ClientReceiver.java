package kr.sys4u.client;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReceiver implements Runnable{

	private final BufferedReader in;
	
	public ClientReceiver(BufferedReader in) {
		this.in = in;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String readMsg = in.readLine();
				System.out.println(readMsg);
			} catch (IOException e) {
				break;
			}
		}
	}
}
