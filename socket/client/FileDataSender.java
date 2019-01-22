package com.socket.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class FileDataSender {
	
	public void sendFileData(File targetFile, OutputStream out) {
		try(FileInputStream fileIn = new FileInputStream(targetFile);
				BufferedOutputStream fileOut = new BufferedOutputStream(out)){
			
			int readByte = 0;
			while((readByte = fileIn.read()) != -1) {
				fileOut.write(readByte);
			}
			fileOut.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
