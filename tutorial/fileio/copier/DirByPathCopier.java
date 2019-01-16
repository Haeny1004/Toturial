package com.tutorial.fileio.copier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.tutorial.fileio.exception.CopyOneFileException;

public class DirByPathCopier implements GenericCopier<String, String>{

	private String targetDirPath;
	private String copiedDirPath;

	public void copy(String targetDirPath, String copiedDirPath) {
		initialize(targetDirPath, copiedDirPath);
		copyChildFileRecursively(new File(targetDirPath));
	}

	public void move(String targetDirPath, String copiedDirPath) {
		initialize(targetDirPath, copiedDirPath);
		moveChildFileRecursively(new File(targetDirPath));
		new File(targetDirPath).delete();
	}

	private void initialize(String targetDirPath, String copiedDirPath) {
		if(targetDirPath == null || copiedDirPath == null) {
			throw new IllegalArgumentException();
		}
		this.targetDirPath = targetDirPath;
		this.copiedDirPath = copiedDirPath;
		copyDirectory(copiedDirPath);
	}
	
	private void copyChildFileRecursively(File parentFile) {
		for (File childFile : parentFile.listFiles()) {
			if (childFile.isDirectory()) {
				copyDirectory(convertAbsoulutPath(childFile));
				copyChildFileRecursively(childFile);
				continue;
			}
			copyOneFile(childFile.getAbsolutePath(), convertAbsoulutPath(childFile));
		}
	}

	private void moveChildFileRecursively(File parentFile) {
		for (File childFile : parentFile.listFiles()) {
			if (childFile.isDirectory()) {
				copyDirectory(convertAbsoulutPath(childFile));
				moveChildFileRecursively(childFile);
				childFile.delete();
				continue;
			}
			copyOneFile(childFile.getAbsolutePath(), convertAbsoulutPath(childFile));
			childFile.delete();
		}
	}

	private String convertAbsoulutPath(File childFile) {
		StringBuffer childFilePath = new StringBuffer(childFile.getAbsolutePath());
		return childFilePath.replace(0, targetDirPath.length(), copiedDirPath).toString();
	}

	private void copyDirectory(String convertAbsoulutPath) {
		new File(convertAbsoulutPath).mkdir();
	}

	private void copyOneFile(String targetPath, String copiedPath) {
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(targetPath)));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(copiedPath)))) {
			int readByte;
			while ((readByte = bis.read()) != -1) {
				bos.write(readByte);
			}
		} catch (IOException e) {
			throw new CopyOneFileException(e);
		}
	}
} // End of class
