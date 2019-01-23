package com.socket.server;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.socket.ReafFile;
import com.socket.exception.CustomServerException;
import com.socket.exception.ExecuteException;
import com.socket.exception.ServerCloseException;

public class ServerMain implements Closeable {

	private static final String SAVE_PATH = "C:/JEONG/saveDir";

	private boolean initialized = false;
	private final int port;
	private ServerSocket serverSocket;
	
	public ServerMain(int port) {
		this.port = port;
	}
	
	private void initialize() throws IOException {
		if(initialized) {
			return;
		}
		this.serverSocket = new ServerSocket(port);
		initialized = true;
	}
	
	private void execute() throws IOException {
		if(!initialized) {
			initialize();
		}
		while(true) {
			// Client가 Server를 죽이면 안되니깐
			try(Socket clientSocket = serverSocket.accept();
					InputStream in = clientSocket.getInputStream();
					OutputStream out = clientSocket.getOutputStream()){
				List<ReafFile> reafList = new ReafInfoReceiver().receiveReafList(in);
				new DirectoryGenerator(SAVE_PATH).generate(reafList, out, in);
			}catch(Exception e) {
				throw new ExecuteException(e);
			}
		}
	}
	
	public void close() {
		if(!initialized) {
			return;
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			throw new ServerCloseException(e);
		}
	}
	
	public static void main(String[] args) {
		try(ServerMain server = new ServerMain(8888);){
			server.initialize();
			server.execute();
		} catch (IOException e) {
			throw new CustomServerException(e);
		}
	}
}
