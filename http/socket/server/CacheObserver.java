package kr.sys4u.http.socket.server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import kr.sys4u.http.socket.server.cache.CacheManager;

public class CacheObserver implements Runnable {

	private CacheManager cacheManager;
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(File cacheFile : cacheManager.getKeySet()) {
				File diskFile = new File(cacheFile.getAbsolutePath());
				if(cacheFile.lastModified() != diskFile.lastModified()) {
					cacheManager.replace(diskFile, getFileData(diskFile));
				}
			}
		}
	}

	private byte[] getFileData(File diskFile) {
		byte[] buf = new byte[8192];
		byte[] data = null;
		try(FileInputStream fis = new FileInputStream(diskFile);
				ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			while(fis.read(buf) != -1) {
				baos.write(buf, 0, 8192);
			}
			data = baos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
