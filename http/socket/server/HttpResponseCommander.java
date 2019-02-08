package kr.sys4u.http.socket.server;

import java.io.File;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import kr.sys4u.http.socket.server.cache.CacheManager;
import kr.sys4u.http.socket.server.commandline.CommandLine;
import kr.sys4u.http.socket.server.response.HtmlTypeResponser;
import kr.sys4u.http.socket.server.response.JpegTypeResponser;
import kr.sys4u.http.socket.server.response.Responser;

public class HttpResponseCommander {

	private static final String WEB_ROOT = "C:/web/workspace";
	private final Map<String, Responser> commandMap = new HashMap<>();
	private final CommandLine commandLine;
	private final Socket clientSocket;
	private boolean initialized = false;
	private CacheManager cacheManager;
	
	public HttpResponseCommander(Socket clientSocket, CommandLine commandLine) {
		this.clientSocket = clientSocket;
		this.commandLine = commandLine;
	}
	
	private void init() {
		if(initialized) {
			return;
		}
		commandMap.put("html", new HtmlTypeResponser(clientSocket));
		commandMap.put("jpg", new JpegTypeResponser(clientSocket));
		cacheManager = CacheManager.getInstance();
		initialized = true;
	}
	
	public void command(){
		if(!initialized) {
			init();
		}
		File targetFile = new File(WEB_ROOT + commandLine.getUrl());
		if(!cacheManager.contains(targetFile)) {
			commandMap.get(commandLine.getExtension()).response(targetFile);
		}else {
			commandMap.get(commandLine.getExtension()).cacheDataResponse(cacheManager.getData(targetFile));
		}
	}
}
