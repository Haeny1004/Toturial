package com.socket;

import java.io.Serializable;

public class ReafFile implements Serializable{

	private static final long serialVersionUID = 6482485808811220534L;
	
	private boolean Directory;
	private String AbsolutePath;
	private String NoRootAbsolutePath;
	private int length;

	public ReafFile(boolean directory, String absolutePath, String NoRootAbsolutePath, int length) {
		this.Directory = directory;
		this.AbsolutePath = absolutePath;
		this.NoRootAbsolutePath = NoRootAbsolutePath;
		this.length = length;
	}

	public String getNoRootAbsolutePath() {
		return NoRootAbsolutePath;
	}

	public void setNoRootAbsolutePath(String noRootAbsolutePath) {
		NoRootAbsolutePath = noRootAbsolutePath;
	}

	public boolean isDirectory() {
		return Directory;
	}

	public void setDirectory(boolean directory) {
		Directory = directory;
	}

	public String getAbsolutePath() {
		return AbsolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		AbsolutePath = absolutePath;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
