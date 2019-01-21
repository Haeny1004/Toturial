package com.tutorial.socket.dirtransport2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tutorial.socket.exception.ReadFileException;

public class FileInfoList {
	
	private final int rootPathLength;
	private final List<FileInfo> fileInfoList = new ArrayList<>();
	
	
	public FileInfoList(String rootPath) {
		if(rootPath == null) {
			throw new IllegalArgumentException();
		}
		this.rootPathLength = rootPath.length();
		initialize(new File(rootPath));
	}
	
	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}

	private void initialize(File rootFile) {
		if(!rootFile.exists()) {
			throw new IllegalArgumentException("rootFile [" + rootFile.getAbsolutePath() + "] is null");
		}
		addChildFileRecursively(rootFile);
	}

	private void addChildFileRecursively(File parentFile) {
		for(File childFile : parentFile.listFiles()) {
			if(childFile.isDirectory()) {
				fileInfoList.add(new FileInfo(true, childFile.getAbsolutePath().substring(rootPathLength), null));
				addChildFileRecursively(childFile);
				continue;
			}
			fileInfoList.add(new FileInfo(false, childFile.getAbsolutePath().substring(rootPathLength), getFileByte(childFile)));
		}
	}

	private byte[] getFileByte(File childFile) {
		byte[] fileByte = null;
		
		try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(childFile))){
			fileByte = new byte[in.available()];
			in.read(fileByte);
		} catch (IOException e) {
			throw new ReadFileException(e);
		}
		return fileByte;
	}

}
