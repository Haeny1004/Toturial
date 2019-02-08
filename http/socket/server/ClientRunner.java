package kr.sys4u.http.socket.server;

import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.sys4u.http.socket.server.commandline.CommandLine;

public class ClientRunner implements Runnable {

	private final Socket clientSocket;
	private final Logger logger;

	public ClientRunner(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.logger = LoggerFactory.getLogger(ClientRunner.class);
	}

	private void execute() {
		try {
			CommandLine commandLine = new HttpRequestReader(clientSocket).read();
            logger.info("commandLine : " + commandLine.getMethod() + commandLine.getUrl() + commandLine.getVersion());
            logger.error("error 출력");
            logger.warn("warn 출력");        
            logger.info("info 출력");
            logger.debug("debug 출력");
            logger.trace("trace 출력"); 
			new HttpResponseCommander(clientSocket, commandLine).command();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 

	@Override
	public void run() {
		execute();
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
