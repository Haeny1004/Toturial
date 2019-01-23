package com.socket.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.socket.ReafFile;
import com.socket.exception.FileDataReceiveAndGenerateException;
import com.socket.exception.SaveFileNotFoundException;

public class DirectoryGenerator {

	private final String savePath;

	public DirectoryGenerator(String savePath) {
		this.savePath = savePath;
	}

	public void generate(List<ReafFile> reafList, OutputStream out, InputStream in) throws IOException {
		DataOutputStream intOut = new DataOutputStream(out);
		
		for (int i = 0; i < reafList.size(); i++) {
			if (reafList.get(i).isDirectory()) {
				makeDirs(reafList.get(i));
			} else {
				makeParentDirs(reafList.get(i));
				intOut.writeInt(i);
				intOut.flush();
				receiveFileData(in, reafList.get(i));
			}
		}
		intOut.writeInt(-1);
	}
	
	private void receiveFileData(InputStream in, ReafFile reafFile) {
		try (BufferedOutputStream fileOut = new BufferedOutputStream(
				new FileOutputStream(convertedPath(reafFile.getNoRootAbsolutePath())))) {
			BufferedInputStream fileIn = new BufferedInputStream(in);
			byte[] buf = new byte[8192];
			int cnt = 0;
			for (int i = 0; i < reafFile.getLength(); i = i + cnt) {
				cnt = fileIn.read(buf);
				fileOut.write(buf, 0, cnt);
				fileOut.flush();
			}
		} catch (FileNotFoundException e) {
			throw new SaveFileNotFoundException(e);
		} catch (IOException e) {
			throw new FileDataReceiveAndGenerateException(e);
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
