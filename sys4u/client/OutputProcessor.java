package kr.sys4u.client;

import java.io.PrintWriter;
import java.util.Scanner;

public class OutputProcessor implements Runnable {

	private final PrintWriter out;
	private final Scanner scan;
	
	public OutputProcessor(PrintWriter out) {
		this.out = out;
		this.scan = new Scanner(System.in);
	}
	
	@Override
	public void run() {
		while(true) {
			String writeMsg = scan.nextLine();
			out.println("TALK|" + writeMsg);
			out.flush();
		}
	}

}
