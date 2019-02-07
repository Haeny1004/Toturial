package kr.sys4u.http.socket.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import kr.sys4u.http.socket.server.responser.Responser;

public class JpgResponser implements Responser {

	private final Socket clientSocket;
	private PrintWriter pw;
	private OutputStream os;
	private FileInputStream fis;
	private boolean initialized = false;
	
	public JpgResponser(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	private void init(String url) {
		if(initialized) {
			return;
		}
		try {
			os = clientSocket.getOutputStream();
			pw = new PrintWriter(new OutputStreamWriter(os));
			fis = new FileInputStream(new File(url));
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
		pw.println("HTTP/1.1 200");
		pw.println("Content-Type: image/jpeg;");
	    pw.println("Connection: close");
	    pw.println("");
	    pw.flush();
	    
	    byte[] buf = new byte[8192];
	    try {
			while(fis.read(buf) != -1) {
				os.write(buf, 0, 8192);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
