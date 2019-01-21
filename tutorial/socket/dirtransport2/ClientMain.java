package com.tutorial.socket.dirtransport2;

public class ClientMain {

	public static void main(String[] args) {
		new DirTransportClient("127.0.0.1", 8888).sendDirectory("C:/JEONG/test");
	}

}
