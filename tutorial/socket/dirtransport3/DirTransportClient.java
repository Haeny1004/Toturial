package com.tutorial.socket.dirtransport3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class DirTransportClient {

	private final String serverIp;
	private final int serverPort;
	
	private Socket c_socket;
	private int targetPathLength;
	private byte[] finallyData;
	
	public DirTransportClient(String serverIp, int serverPort) {
		this.serverIp = serverIp;
		this.serverPort = serverPort;
	}
	
	private void initialize(String targetPath) {
		try {
			c_socket = new Socket(serverIp, serverPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.targetPathLength = targetPath.length();
	}
	
	public void transporter(String targetPath) {
		initialize(targetPath);
		addChildFileDataRecursively(new File(targetPath));
	}
	

	private void addChildFileDataRecursively(File parentFile) {
		for(File childFile : parentFile.listFiles()) {
			if(childFile.isDirectory()) {
				addFileInfoByte(childFile);
				
				continue;
			}
		}
		
	}

	private void addFileInfoByte(File childFile) {
		byte[] isDirByte = String.valueOf(childFile.isDirectory()).getBytes();
		byte[] filePathByte = childFile.getAbsolutePath().substring(targetPathLength).getBytes();
		byte[] fileDataByte = readFileData(childFile);
		byte[] fileDataLengthByte = String.valueOf(fileDataByte.length).getBytes();
		
		
		byte[] temp = new byte[finallyData.length + isDirByte.length];
	}

	private byte[] readFileData(File childFile) {
		byte[] fileData = null;
		
		try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(childFile))){
			fileData = new byte[in.available()];
			in.read(fileData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileData;
	}

	public static void main(String[] args) {
		
	}

}
