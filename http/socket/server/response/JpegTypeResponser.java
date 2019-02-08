package kr.sys4u.http.socket.server.response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import kr.sys4u.http.socket.server.cache.CacheManager;

public class JpegTypeResponser implements Responser {

	private final Socket clientSocket;
	private PrintWriter pw;
	private boolean initialized = false;

	public JpegTypeResponser(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	private void init() {
		if (initialized) {
			return;
		}
		try {
			pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
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

	private void responseBody(File targetFile) {
		byte[] buf = new byte[8192];
		try (FileInputStream fis = new FileInputStream(targetFile);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				OutputStream os = clientSocket.getOutputStream();) {
			while (fis.read(buf) != -1) {
				os.write(buf, 0, 8192);
				baos.write(buf, 0, 8192);
				os.flush();
			}
			CacheManager.getInstance().put(targetFile, baos.toByteArray());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void responseHeader() {
		if(!initialized) {
			init();
		}
		pw.println("HTTP/1.1 200");
		pw.println("Content-Type: image/jpeg;");
		pw.println("Connection: close");
		pw.println("");
		pw.flush();
	}

	@Override
	public void cacheDataResponse(byte[] data) {
		responseHeader();
		try (OutputStream os = clientSocket.getOutputStream()) {
			os.write(data);
			os.flush();
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
