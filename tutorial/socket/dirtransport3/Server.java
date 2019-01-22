package com.tutorial.socket.dirtransport3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static final String SAVE_PATH = "C:/JEONG/test2";
	
	private final ServerSocket server;
	private final Socket serverSocket;
	
	public Server(int port) throws IOException {
		this.server = new ServerSocket(port);
		this.serverSocket = server.accept();
	}
	
	public void receiveDir() {
		try (PrintWriter outMessage = new PrintWriter(serverSocket.getOutputStream());
				BufferedReader inMessage = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()))){
			
			String readLine = null;
			while((readLine = inMessage.readLine()) != null) {
				System.out.println("MessageFromClient >" + readLine);
				if(readLine.charAt(0) == '1') {
					String convertedfilePath = convertPath(readLine.substring(0));
					makeDirs(convertedfilePath);
					outMessage.flush();
				}else {
					String convertedfilePath = convertPath(readLine.substring(0));
					makeParentDirs(convertedfilePath);
					outMessage.write(readLine);
					outMessage.flush();
					receiveOneFileData(convertedfilePath);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void makeDirs(String convertedfilePath) {
		new File(convertedfilePath).mkdirs();
	}

	private void receiveOneFileData(String savefilePath) {
		try(BufferedInputStream dataIn = new BufferedInputStream(serverSocket.getInputStream());
				BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(new File(savefilePath)))){
			int readByte = 0;
			while((readByte = dataIn.read()) != -1) {
				fileOut.write(readByte);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void makeParentDirs(String convertPath) {
		new File(convertPath).getParentFile().mkdirs();
	}

	private String convertPath(String beforePath) {
		return SAVE_PATH + beforePath;
	}
	
	public static void main(String[] args) {
		try{
			Server server = new Server(8888);
			server.receiveDir();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
