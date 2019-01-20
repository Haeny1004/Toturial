package com.tutorial.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class DirTransportClient {

	private static final String dirPath = "C:/dirTreeTest";
	private static final File sourceDir = new File(dirPath);
	private static final byte[] dirMessage = new String("[Dir ]").getBytes();
	private static final byte[] fileMessage = new String("[File]").getBytes();
	
	public static void main(String[] args) {
		transport();
	}
	
	static void transport() {
		childFileTransportRecursively(sourceDir);
	}

	private static void childFileTransportRecursively(File parentFile) {
		for (File childFile : parentFile.listFiles()) {
			if(childFile.isDirectory()) {
//				transportDir(childFile.getName().getBytes());
				childFileTransportRecursively(childFile);
			}
			transportFile(childFile);
		}
	}

	private static void transportFile(File childFile) {
		try(Socket c_socket = new Socket("127.0.0.1", 8888)){

			BufferedInputStream in = new BufferedInputStream(new FileInputStream(childFile));
			BufferedOutputStream out = new BufferedOutputStream(c_socket.getOutputStream());
			
			byte[] filePathMessage = new byte[1024];
			byte[] childFilePath = childFile.getAbsolutePath().getBytes();
			for(int i = 0; i < childFile.getAbsolutePath().length(); i++) {
				filePathMessage[i] = childFilePath[i];
			}
			byte[] fileData = new byte[6 + 1024 + in.available()];
			addFileHeader(fileData, filePathMessage);
			
			in.read(fileData, 1030, in.available());
			
			out.write(fileData);
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addFileHeader(byte[] fileData, byte[] filePathMessage) {
		for(int i = 0; i < fileMessage.length; i++) {
			fileData[i] = fileMessage[i];
		}
		
		for(int i = 0; i < filePathMessage.length; i++) {
			fileData[i + 6] = filePathMessage[i];
		}
	}

	private static void transportDir(File childFile) {
		try(Socket c_socket = new Socket("127.0.0.1", 8888)){

			BufferedOutputStream out = new BufferedOutputStream(c_socket.getOutputStream());
			String childPath = childFile.getAbsolutePath();
//			byte[] fileData = new byte[dirMessage.length + childDirName.length];
//			addDirMessage(fileData);
//			for(int i = 0; i < childDirName.length; i++) {
//				fileData[dirMessage.length + i] = childDirName[i];
//			}
			
//			out.write(fileData);
//			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addDirMessage(byte[] fileData) {
		for(int i = 0; i < dirMessage.length; i++) {
			fileData[i] = dirMessage[i];
		}
	}
	
	
	
	

}
