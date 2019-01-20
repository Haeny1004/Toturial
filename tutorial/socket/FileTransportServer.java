package com.tutorial.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransportServer {

	private static final String savePath = "C:/Temp/test.txt";

	public static void main(String[] args) {

		try (ServerSocket server = new ServerSocket(8888);
				Socket c_socket = server.accept();
				BufferedInputStream in = new BufferedInputStream(c_socket.getInputStream());
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(savePath)));) {

			byte[] receiveData = new byte[in.available()];
			in.read(receiveData);
			out.write(receiveData);
			out.flush();

			c_socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
