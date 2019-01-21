package com.tutorial.socket.filetransport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import com.tutorial.socket.exception.FileTransportException;

public class FileTransportClient {

	private static final String TARGET_PATH = "C:/JEONG/file-structure.txt";

	public static void main(String[] args) {
		
		try(Socket c_socket = new Socket("127.0.0.1", 8888);
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(TARGET_PATH)));
				BufferedOutputStream out = new BufferedOutputStream(c_socket.getOutputStream())){
		
			byte[] fileData = new byte[in.available()];
			in.read(fileData);
			
			out.write(fileData);
			out.flush();
		} catch (IOException e) {
			throw new FileTransportException(e);
		}
	}
} // End of class
