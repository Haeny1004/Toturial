package com.tutorial.socket.dirtransport;

import java.io.Serializable;

public class FileInfo implements Serializable{ 
	
	private static final long serialVersionUID = -5829369081926580889L;
	private boolean isDirectory = true;
	private String filePath;
	private byte[] fileData;
	
	public FileInfo(boolean isDirectory, String filePath, byte[] fileData) {
		this.isDirectory = isDirectory;
		this.filePath = filePath;
		this.fileData = fileData;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
}
