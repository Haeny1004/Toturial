package kr.sys4u.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Closeable{

	private final String serverIp;
	private final int serverPort;
	private boolean initialized = false;
	private Socket clientSocket;
	
	public Client(String serverIp, int serverPort) {
		this.serverIp = serverIp;
		this.serverPort = serverPort;
	}
	
	private void init() throws UnknownHostException, IOException {
		if(initialized) {
			return;
		}
		clientSocket = new Socket(serverIp, serverPort);
		initialized = true;
	}
	
	private void execute(String clientName) throws UnknownHostException, IOException {
		if(!initialized) {
			init();
		}
		new ClientExecutor(clientSocket).execute(clientName);
	}
	
	@Override
	public void close() {
		if(!initialized) {
			return;
		}
	}
	
	public static void main(String[] args) {
		String clientName = new ClientLogin().login();
		try(Client client = new Client("127.0.0.1", 8888)){
			client.init();
			client.execute(clientName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
