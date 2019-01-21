package com.tutorial.socket.dirtransport;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.tutorial.socket.exception.WriteObjectException;

public class DirTransportClient {

	private static final String ROOT_PATH = "C:/JEONG/test";

	public static void main(String[] args) {
		try (Socket c_socket = new Socket("127.0.0.1", 8888);
				ObjectOutputStream out = new ObjectOutputStream(c_socket.getOutputStream())) {
			out.writeObject(new FileInfoList(ROOT_PATH).getFileInfoList());
			out.flush();
		} catch (IOException e) {
			throw new WriteObjectException(e);
		}
	}
} // End of class
