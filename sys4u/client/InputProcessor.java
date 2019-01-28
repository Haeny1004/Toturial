package kr.sys4u.client;

import java.io.BufferedReader;
import java.io.IOException;

public class InputProcessor implements Runnable{

	private final BufferedReader in;
	
	public InputProcessor(BufferedReader in) {
		this.in = in;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String readMsg = in.readLine();
				System.out.println(readMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
