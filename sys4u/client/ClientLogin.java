package kr.sys4u.client;

import java.util.Scanner;

public class ClientLogin {

	private Scanner scan;
	
	private void init() {
		scan = new Scanner(System.in);
	}
	
	public String login() {
		init();
		System.out.println("=========Chatting==========");
		System.out.print("NickName : ");
		String name = scan.nextLine().trim();
		return "INFO|DEFAULT@" + name;
	}
}
