package com.socket.client;

import java.io.File;
import java.util.ArrayList;

public class ReafFileFinder {

	private final String targetDirPath;
	private final ArrayList<File> reafFileList = new ArrayList<>();
	
	
	public ReafFileFinder(String targetDirPath) {
		this.targetDirPath = targetDirPath;
	}
	
	private void initialize() {
		addReafFileRecursively(new File(targetDirPath));
	}

	public ArrayList<File> getReafFileList(){
		initialize();
		return reafFileList;
	}
	
	private void addReafFileRecursively(File parentFile) {
		if(parentFile.listFiles().length == 0) {
			reafFileList.add(parentFile);
			return;
		}
		
		for(File childFile : parentFile.listFiles()) {
			if(childFile.isDirectory()) {
				addReafFileRecursively(childFile);
				continue;
			}
			reafFileList.add(childFile);
		}
	}
	
	public void printReafFile() {
		for (File reafFile : reafFileList) {
			System.out.println(reafFile.isDirectory() + reafFile.getName());
		}
	}
}
