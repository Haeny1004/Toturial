package com.tutorial.socket.dirtransport3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DirTransportServer {

	public static void main(String[] args) {

		try(ServerSocket server = new ServerSocket(8888);
				Socket s_socket = server.accept();
				){
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
