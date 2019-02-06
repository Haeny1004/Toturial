package kr.sys4u.http.socket;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Closeable{

	private Socket clientSocket;
	private boolean initialized = false;
	
	private void init() throws IOException {
		if(initialized) {
			return;
		}
		try {
//			http://wiki2.co.kr/
			clientSocket = new Socket("www.naver.com",443);
			System.out.println("Socket Connection Success");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		initialized = true;
	}
	
	private void execute() throws IOException {
		if(!initialized) {
			init();
		}
		new ClientExecutor(clientSocket).execute();
	}
	
	@Override
	public void close() throws IOException {
		if(!initialized) {
			return;
		}
		clientSocket.close();
	}
	
	public static void main(String[] args) {
		try(Client client = new Client()){
			client.init();
			client.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
