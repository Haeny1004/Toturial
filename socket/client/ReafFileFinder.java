package com.socket.client;

import java.io.File;
import java.util.ArrayList;

import com.socket.ReafFile;

public class ReafFileFinder {

	private final String targetDirPath;
	private final ArrayList<ReafFile> reafFileList = new ArrayList<>();

	public ReafFileFinder(String targetDirPath) {
		this.targetDirPath = targetDirPath;
	}

	private void initialize() {
		addReafFileRecursively(new File(targetDirPath));
	}

	public ArrayList<ReafFile> getReafFileList() {
		initialize();
		return reafFileList;
	}

	private void addReafFileRecursively(File parentFile) {
		if (parentFile.listFiles().length == 0) {
			reafFileList.add(new ReafFile(parentFile.isDirectory(), parentFile.getAbsolutePath(),
					parentFile.getAbsolutePath().substring(targetDirPath.length()), (int) parentFile.length()));
			return;
		}

		for (File childFile : parentFile.listFiles()) {
			if (childFile.isDirectory()) {
				addReafFileRecursively(childFile);
				continue;
			}
			reafFileList.add(new ReafFile(childFile.isDirectory(), childFile.getAbsolutePath(),
					childFile.getAbsolutePath().substring(targetDirPath.length()), (int) childFile.length()));
		}
	}
	

}
