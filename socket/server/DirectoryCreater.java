package com.socket.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.socket.ReafFile;

public class DirectoryCreater {
	
	private final String savePath;
	
	public DirectoryCreater(String savePath) {
		this.savePath = savePath;
	}
	
	public void createDirectory(ArrayList<ReafFile> reafList, OutputStream out, InputStream in) throws IOException {
		for (int i = 0; i < reafList.size(); i++) {
			if (reafList.get(i).isDirectory()) {
				makeDirs(reafList.get(i));
			} else {
				makeParentDirs(reafList.get(i));
				out.write(i);
				out.flush();
				receiveFileData(in, reafList.get(i));
			}
		}
	}

	private void receiveFileData(InputStream in, ReafFile reafFile) {
		try (BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(convertedPath(reafFile.getNoRootAbsolutePath())))) {
			BufferedInputStream fileIn = new BufferedInputStream(in);
			byte[] buf = new byte[8192];
			for(int i = 0; i < reafFile.getLength(); i = i + 8192) {
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

	private void makeParentDirs(ReafFile reafFile) {
		new File(convertedPath(reafFile.getNoRootAbsolutePath())).getParentFile().mkdirs();
	}

	private void makeDirs(ReafFile reafFile) {
		new File(convertedPath(reafFile.getNoRootAbsolutePath())).mkdirs();
	}

	private String convertedPath(String absolutePath) {
		return savePath + absolutePath;
	}
	
	
}
