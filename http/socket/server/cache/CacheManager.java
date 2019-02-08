package kr.sys4u.http.socket.server.cache;

import java.io.File;
import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;

public class CacheManager {

	private static volatile CacheManager instance = new CacheManager();
	private TreeMap<File, byte[]> cache;
	
	private CacheManager() {
		this.cache = new TreeMap<>();
	}
	
	public static CacheManager getInstance() {
		return instance;
	}
	
	public synchronized void put(File file, byte[] data) {
		cache.put(file, data);
		System.out.println("[Cache] Put File : " + file.getName());
	}

	public synchronized boolean contains(File targetFile) {
		System.out.println(cache.containsKey(targetFile));
		return cache.containsKey(targetFile);
	}

	public synchronized byte[] getData(File targetFile) {
		return cache.get(targetFile);
	}

	public synchronized Set<File> getKeySet() {
		return Collections.unmodifiableSet(cache.keySet());
	}
	
	public synchronized void replace(File file, byte[] data) {
		cache.replace(file, data);
	}
	
}
