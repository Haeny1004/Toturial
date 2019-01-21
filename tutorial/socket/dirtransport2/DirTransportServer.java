package com.tutorial.socket.dirtransport2;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.tutorial.socket.exception.ConnectServerException;
import com.tutorial.socket.exception.ReadObjectException;

public class DirTransportServer {

	private static final String SAVE_ROOT_PATH = "C:/JEONG/test2";
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(8888);
				Socket s_socket = server.accept();
				ObjectInputStream in = new ObjectInputStream(s_socket.getInputStream());) {
			
			new DirTransportServer().createChildrenFile((List<FileInfo>)in.readObject());
			
		} catch (IOException e) {
			throw new ConnectServerException(e);
		} catch (ClassNotFoundException e) {
			throw new ReadObjectException(e);
		}
	}
	
	private void createChildrenFile(List<FileInfo> fileList) {
		for(FileInfo fileInfo : fileList) {
			if(fileInfo.isDirectory()) {
				new File(addRootPath(fileInfo.getFilePath())).mkdir();
				continue;
			}
			createChileFile(fileInfo);
		}
	}

	private void createChileFile(FileInfo fileInfo) {
		try(BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(addRootPath(fileInfo.getFilePath()))))){
			out.write(fileInfo.getFileData());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String addRootPath(String filePath) {
		String absolutePath = SAVE_ROOT_PATH.concat(filePath);
		return absolutePath;
	}

} // End of class
