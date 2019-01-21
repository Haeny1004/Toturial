package com.tutorial.socket.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EchoClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		try(Socket c_socket = new Socket("127.0.0.1", 8888)){
			System.out.println(getTime() + "Accept to Server Success");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(c_socket.getInputStream(), "UTF-8"));
			PrintWriter out = new PrintWriter(c_socket.getOutputStream());
			
			System.out.println("now, you can chat!");
			String echoMessage = "";
			
			while(!echoMessage.equals("exit")) {
				System.out.print(getTime() + "To server > ");
				echoMessage = scan.nextLine();
				
				out.println(echoMessage);
				out.flush();
				System.out.println(getTime() + "From Server > " + in.readLine());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scan.close();
	}
	
	static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
		return sdf.format(new Date());
	}

}
