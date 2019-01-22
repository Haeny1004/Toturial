package com.socket.client;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		try(Socket clientSocket = new Socket("127.0.0.1", 8888)){
			
			ReafFileFinder finder = new ReafFileFinder("C:/JEONG/test");
			ArrayList<File> reafFileList = finder.getReafFileList();
			
			new ReafInfoSender().sendReadList(reafFileList, clientSocket.getOutputStream());
			
			int readByte = 0;
			while((readByte = clientSocket.getInputStream().read()) != -1) {
				System.out.println(readByte);
//				new FileDataSender().sendFileData();
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
