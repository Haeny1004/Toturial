package com.tutorial.socket.dirtransport2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.tutorial.socket.exception.InvalidIpException;
import com.tutorial.socket.exception.NoConnectServerException;
import com.tutorial.socket.exception.WriteObjectException;

public class DirTransportClient {

	private final String serverIp;
	private final int serverPort;
	private Socket c_socket;
	
	public DirTransportClient(String serverIp, int serverPort) {
		this.serverIp = serverIp;
		this.serverPort = serverPort;
	}
	
	public void sendDirectory(String dirPath) {
		connectServer(serverIp, serverPort);
		try(ObjectOutputStream out = new ObjectOutputStream(c_socket.getOutputStream())){
			out.writeObject(new FileInfoList(dirPath).getFileInfoList());
			out.flush();
		} catch (IOException e) {
			throw new WriteObjectException(e);
		}
	}
	
	private void connectServer(String ip, int port) {
		try {
			this.c_socket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			throw new InvalidIpException("[" + ip + "] 에 해당하는 Host가 존재하지 않습니다.", e);
		} catch (IOException e) {
			throw new NoConnectServerException(e);
		}
	}
	
} // End of class
