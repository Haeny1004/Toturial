package com.tutorial.socket.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EchoServer {

	public static void main(String[] args) {

		try (ServerSocket server = new ServerSocket(8888)) {
			System.out.println(getTime() + "Server is ready...");
			System.out.println(getTime() + "Wait for client");
			
			Socket s_socket = server.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(s_socket.getInputStream()));
			PrintWriter out = new PrintWriter(s_socket.getOutputStream());
//			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s_socket.getOutputStream()));
			
			String readLine = "";
			
			while((readLine = in.readLine()) != null) {
				System.out.println(getTime() + "From Client > " + readLine);
				out.println(readLine);
//				out.write(readLine);
				out.flush();
			}
			
			s_socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
		return sdf.format(new Date());
	}
}
