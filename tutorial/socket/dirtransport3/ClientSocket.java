package com.tutorial.socket.dirtransport3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ClientSocket {

	private final Socket clientSocket;
	private final List<String> fileInfoList = new ArrayList<>();

	private int targetPathLength;

	public ClientSocket(String serverIp, int serverPort) throws UnknownHostException, IOException {
		this.clientSocket = new Socket(serverIp, serverPort);
	}

	public void transporter(String targetPath) {
		targetPathLength = targetPath.length();
		findReafRecursively(new File(targetPath));

		try (PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
			for (String fileInfo : fileInfoList) {
				out.write(fileInfo);
				out.flush();
				String receiveFilePath = null;
				if ((receiveFilePath = in.readLine()) != null) {
					System.out.println("MessageFromServer >" + receiveFilePath);
					sendFileData(receiveFilePath);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendFileData(String receiveFilePath) {
		try (BufferedOutputStream dataOut = new BufferedOutputStream(clientSocket.getOutputStream());
				BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(new File(receiveFilePath)))) {
			int readByte = 0;
			while ((readByte = fileIn.read()) != -1) {
				dataOut.write(readByte);
				dataOut.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void findReafRecursively(File parentFile) {
		for (File childFile : parentFile.listFiles()) {
			if (childFile.listFiles() != null) {
				findReafRecursively(childFile);
				continue;
			}
			if (childFile.isDirectory()) {
				String temp = 1 + childFile.getAbsolutePath().substring(targetPathLength);
				fileInfoList.add(temp);
			} else {
				String temp = 0 + childFile.getAbsolutePath().substring(targetPathLength);
				fileInfoList.add(temp);
			}
		}
	}

	public static void main(String[] args) {
		try {

			ClientSocket client = new ClientSocket("127.0.0.1", 8888);
			client.transporter("C:/JEONG/test");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
	}

}
