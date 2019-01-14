package com.tutorial.fileio;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DirNode {

	private final int depth;
	private final File currentDir;
	private final List<DirNode> childDir;
	
	public DirNode(final int depth, final File currentDir) {
		this.depth = depth;
		this.currentDir = currentDir;
		this.childDir = new ArrayList<>();
	}
	
	public int getDepth() {
		return depth;
	}
	
	public File getCurrentDir() {
		return currentDir;
	}
	
	public boolean addChild(DirNode childDS) {
		if(childDS == null) {
			throw new IllegalArgumentException();
		}
		return this.childDir.add(childDS);
	}
	
	public boolean addChildren(Collection<DirNode> children) {
		if(children == null) {
			throw new IllegalArgumentException();
		}
		return this.childDir.addAll(children);
	}
	
	/* 
	 * 조회기능을 위한 방법
	 * 1. 복사
	 * 2. UnModifiable ??
	 * 
	 * */
	public List<DirNode> getChildDir(){
		List<DirNode> cloned = new ArrayList<>();
		cloned.addAll(this.childDir);
		return cloned;
	}
	
	public void removeChildren() {
		this.childDir.removeAll(this.childDir);
	}
}
