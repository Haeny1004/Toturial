package kr.sys4u.http.socket.server.responser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HtmlResponser implements Responser {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private boolean initialized = false;
	
	public HtmlResponser(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	private void init(String url) {
		if(initialized) {
			return;
		}
		try {
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(url)), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialized = true;
	}
	
	@Override
	public void response(String url) {
		if(!initialized) {
			init(url);
		}
		out.println("HTTP/1.1 200");
		out.println("Content-Type: text/html; charset=UTF-8"); 
	    out.println("Connection: close");
	    out.println("");
	    
	    String readLine = null;
	    try {
			while((readLine = in.readLine()) != null) {
				out.println(readLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    out.flush();
	}
}
