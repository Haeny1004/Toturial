package kr.sys4u.http.socket.server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import kr.sys4u.http.socket.server.commandline.CommandLine;
import kr.sys4u.http.socket.server.responser.HtmlResponser;
import kr.sys4u.http.socket.server.responser.Responser;

public class HttpResponseCommander {

	private final Map<String, Responser> commandMap = new HashMap<>();
	private final Socket clientSocket;
	private final CommandLine commandLine;
	private boolean initialized = false;
	
	public HttpResponseCommander(Socket clientSocket, CommandLine commandLine) {
		this.clientSocket = clientSocket;
		this.commandLine = commandLine;
	}
	
	private void init() {
		if(initialized) {
			return;
		}
		commandMap.put("html", new HtmlResponser(clientSocket));
		commandMap.put("jpg", new JpgResponser(clientSocket));
		initialized = true;
	}
	
	public void command() throws IOException {
		if(!initialized) {
			init();
		}
		commandMap.get(commandLine.getExtension()).response(commandLine.getUrl());
	}
}
