package com.tutorial.socket.dirtransport3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class DirTransportClient {

	private final String serverIp;
	private final int serverPort;

	private int targetPathLength;

	public DirTransportClient(String serverIp, int serverPort) {
		this.serverIp = serverIp;
		this.serverPort = serverPort;
	}

	public void transporter(String targetPath) {
		this.targetPathLength = targetPath.length();
		try (Socket c_socket = new Socket(serverIp, serverPort);
				DataOutputStream out = new DataOutputStream(new BufferedOutputStream(c_socket.getOutputStream()));){
			writeChildFileDataRecursively(new File(targetPath), out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeChildFileDataRecursively(File parentFile, DataOutputStream out) {
		for (File childFile : parentFile.listFiles()) {
			if (childFile.isDirectory()) {
				writeDirInfoByte(childFile, out);
				writeChildFileDataRecursively(childFile, out);
				continue;
			}
			writeFileInfoByte(childFile, out);
		}
	}

	private void writeFileInfoByte(File childFile, DataOutputStream out) {
		String filePath = childFile.getAbsolutePath().substring(targetPathLength);
		int filePathLength = filePath.getBytes().length;
		long fileDataLength = childFile.length();
		byte[] fileDataByte = readFileData(childFile);

		try { 
			out.writeBoolean(false);
			out.writeInt(filePathLength);
			out.writeBytes(filePath);
			out.writeLong(fileDataLength);
			out.write(fileDataByte);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void writeDirInfoByte(File childFile, DataOutputStream out) {
		String filePath = childFile.getAbsolutePath().substring(targetPathLength);
		int filePathLength = filePath.getBytes().length;

		try{
			out.writeBoolean(true);
			out.writeInt(filePathLength);
			out.writeBytes(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] readFileData(File childFile) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream((int)childFile.length());
		byte[] buf = new byte[8192];
		
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(childFile))) {
			int cnt = in.read(buf);
			baos.write(buf, 0, cnt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static void main(String[] args) {
		new DirTransportClient("127.0.0.1", 8888).transporter("C:/JEONG/test");
	}

}
