package com.socket.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.socket.ReafFile;

public class ReafInfoSender {
	
	public void sendReadInfo(ArrayList<ReafFile> reafFileList, OutputStream out, InputStream in) throws IOException {
		sendReafList(reafFileList, out);
		
		int readByte = 0;
		while((readByte = in.read()) != -1) {
			new ReafInfoSender().sendFileData(new File(reafFileList.get(readByte).getAbsolutePath()), out);
		}
	}
	
	private void sendReafList(ArrayList<ReafFile> reafList, OutputStream out) {
		try{
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(reafList);
			objOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendFileData(File targetFile, OutputStream out) {
		try (BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(targetFile));) {
			BufferedOutputStream fileOut = new BufferedOutputStream(out);
			
			byte[] buf = new byte[8192];
			for(int i = 0; i < targetFile.length(); i = i + 8192) {
				fileIn.read(buf);
				fileOut.write(buf);
			}
			fileOut.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
