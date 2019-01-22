package com.socket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.socket.ReafFile;

public class ClientMain {

	public static void main(String[] args) {
		
		try(Socket clientSocket = new Socket("127.0.0.1", 8888);
				OutputStream out = clientSocket.getOutputStream();
				InputStream in = clientSocket.getInputStream()){
			
			ArrayList<ReafFile> reafFileList = new ReafFileFinder("C:/targetDir").getReafFileList();
			new ReafInfoSender().sendReadInfo(reafFileList, out, in);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
