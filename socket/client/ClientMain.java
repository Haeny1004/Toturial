package com.socket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.socket.ReafFile;
import com.socket.exception.HostConnectException;
import com.socket.exception.StreamGetterException;

public class ClientMain {

	public static void main(String[] args) {
		
		try(Socket clientSocket = new Socket("127.0.0.1", 8888);
				OutputStream out = clientSocket.getOutputStream();
				InputStream in = clientSocket.getInputStream()){
			
			List<ReafFile> reafFileList = new ReafFileCollector("C:/JEONG/targetDir").getReafFileList();
			new ReafInfoSender().sendReafInfo(reafFileList, out, in);
		} catch (UnknownHostException e) {
			throw new HostConnectException(e);
		} catch (IOException e) {
			throw new StreamGetterException(e);
		}
	}
}
