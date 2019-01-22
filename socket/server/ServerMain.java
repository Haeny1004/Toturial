package com.socket.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.socket.ReafFile;

public class ServerMain {

	private static final String SAVE_PATH = "C:/saveDir";

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(8888);
				Socket serverSocket = server.accept();
				InputStream in = serverSocket.getInputStream();
				OutputStream out = serverSocket.getOutputStream()) {

			ArrayList<ReafFile> reafList = new ReafInfoReceiver().receiveReafList(serverSocket.getInputStream());
			new DirectoryCreater(SAVE_PATH).createDirectory(reafList, out, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
