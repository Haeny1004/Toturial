package com.tutorial.socket.filetransport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.tutorial.socket.exception.FileTransportException;

public class FileTransportServer {

	private static final String SAVE_PATH = "C:/JEONG/file-structure-tranport.txt";

	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(8888);
				Socket s_socket = server.accept();
				BufferedInputStream in = new BufferedInputStream(s_socket.getInputStream());
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(SAVE_PATH)))){
			
			byte[] fileData = new byte[in.available()];
			in.read(fileData);
			out.write(fileData);
		} catch (IOException e) {
			throw new FileTransportException(e);
		}
		
	}

}
