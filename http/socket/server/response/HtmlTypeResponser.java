package kr.sys4u.http.socket.server.response;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import kr.sys4u.http.socket.server.cache.CacheManager;

public class HtmlTypeResponser implements Responser {

	private Socket clientSocket;
	private PrintWriter out;
	private boolean initialized = false;

	public HtmlTypeResponser(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	private void init() {
		if (initialized) {
			return;
		}
		try {
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialized = true;
	}

	@Override
	public void response(File targetFile) {
		responseHeader();
		responseBody(targetFile);
		close();
	}

	private void responseHeader() {
		if(!initialized) {
			init();
		}
		out.println("HTTP/1.1 200");
		out.println("Content-Type: text/html; charset=UTF-8");
		out.println("Connection: close");
		out.println("");
		out.flush();
	}

	private void responseBody(File targetFile) {
		String readLine = null;
		StringBuffer readLines = new StringBuffer();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile), "UTF-8"));) {
			while ((readLine = in.readLine()) != null) {
				out.println(readLine);
				readLines.append(readLine);
			}
			out.flush();
			CacheManager.getInstance().put(targetFile, readLines.toString().getBytes());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cacheDataResponse(byte[] data) {
		responseHeader();
		try (BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream())) {
			out.write(data);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		close();
	}
	
	private void close() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
