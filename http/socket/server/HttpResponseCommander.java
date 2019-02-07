package kr.sys4u.http.socket.server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
		initialized = true;
	}
	
	public void command() throws IOException {
		if(!initialized) {
			init();
		}
		commandMap.get(commandLine.getExtension()).response(commandLine.getUrl());
		
		
		
//		if(startLine.getUrl().equals("/index.html")) {
//			pw.println("HTTP/1.1 200");
//			pw.println("Content-Type: text/html; charset=UTF-8"); 
//		    pw.println("Connection: close");
//		    pw.println("");
//		    
//		    String readLine = null;
//		    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//		    while((readLine = reader.readLine()) != null) {
//		    	pw.println(readLine);
//		    }
//		    pw.flush();
//		    return;
//		}
//		
//		pw.println("HTTP/1.1 200");
//		pw.println("Content-Type: image/jpeg;");
//	    pw.println("Connection: close");
//	    pw.println("");
//	    pw.flush();
//	    byte[] buf = new byte[1024];
//	    while(fis.read(buf) != -1) {
//	    	out.write(buf, 0, 1024);
//	    	out.flush();
//	    }
	}
}
