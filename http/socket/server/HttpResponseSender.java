package kr.sys4u.http.socket.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpResponseSender {

	private final Socket clientSocket;
	private final StartLine startLine;
	private PrintWriter pw;
	private OutputStream out;
	private FileInputStream fis;
	private boolean initialized = false;
	
	public HttpResponseSender(Socket clientSocket, StartLine startLine) {
		this.clientSocket = clientSocket;
		this.startLine = startLine;
	}
	
	private void init() {
		if(initialized) {
			return;
		}
		try {
			pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			out = clientSocket.getOutputStream();
			fis = new FileInputStream(new File("C:/web/workspace" + startLine.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialized = true;
	}
	
	public void send() throws IOException {
		if(!initialized) {
			init();
		}
		
		if(startLine.getPath().equals("/index.html")) {
			pw.println("HTTP/1.1 200");
			pw.println("Content-Type: text/html; charset=UTF-8"); 
		    pw.println("Connection: close");
		    pw.println("");
		    
		    String readLine = null;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		    while((readLine = reader.readLine()) != null) {
		    	pw.println(readLine);
		    }
		    pw.flush();
		    return;
		}
		
		pw.println("HTTP/1.1 200");
		pw.println("Content-Type: image/jpeg;");
	    pw.println("Connection: close");
	    pw.println("");
	    pw.flush();
	    byte[] buf = new byte[1024];
	    while(fis.read(buf) != -1) {
	    	out.write(buf, 0, 1024);
	    	out.flush();
	    }
	}
}
