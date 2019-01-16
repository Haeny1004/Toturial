package com.tutorial.fileio.copier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OneFileByPathCopier {
	
	private File targetFile;
	
	public void copy(String targetPath, String copiedPath) {
		initialize(targetPath);
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(targetFile));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(copiedPath)))){
			int readByte;
			while((readByte = bis.read()) != -1) {
				bos.write(readByte);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void move(String targetPath, String copiedPath) {
		this.copy(targetPath, copiedPath);
		targetFile.delete();
	}
	
	private void initialize(String targetPath) {
		this.targetFile = new File(targetPath); 
	}
} // End of class
